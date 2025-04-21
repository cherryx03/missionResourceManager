import java.util.*;

public class Calculator {

    public enum TimeUnit {
        DAILY,
        HOURLY
    }

    public static class ActivitySchedule {
        public Set<Integer> activeDays;
        public Set<Integer> activeHours;

        public ActivitySchedule() {
            this.activeDays = new HashSet<>();
            this.activeHours = new HashSet<>();
        }
    }

    public Calculator() {}

    public double getCalorieRate(CrewMember cm, boolean isActive) {
        double base = 3035; // kcal/day baseline
        if (cm.getSex().equalsIgnoreCase("female") || cm.getSex().equalsIgnoreCase("f")) base *= 0.85;
        base += (cm.getWeight() - 75) * 10;
        if (isActive) base *= 1.2;
        return base;
    }

    public double getWaterRate(CrewMember cm, boolean isActive) {
        double base = 2.5; // L/day baseline
        base += (cm.getWeight() - 75) * 0.01;
        base += (cm.getAge() - 30) * 0.005;
        if (isActive) base *= 1.3;
        return base;
    }

    public double getOxygenRate(CrewMember cm, boolean isActive) {
        double base = 1.083; // kg/day baseline
        if (cm.getSex().equalsIgnoreCase("female") || cm.getSex().equalsIgnoreCase("f")) base *= 0.90;
        base += (cm.getWeight() - 75) * 0.01;
        if (isActive) base += 0.376;
        return base;
    }

    public double[][] getResourceOverTime(List<CrewMember> crew, TimeUnit unit, int duration, Map<CrewMember, ActivitySchedule> activityMap, String type) {
        int steps = unit == TimeUnit.DAILY ? duration : duration * 24;
        double[][] data = new double[steps + 1][crew.size()];

        for (int t = 0; t <= steps; t++) {
            for (int i = 0; i < crew.size(); i++) {
                CrewMember cm = crew.get(i);
                boolean isActive = false;
                if (activityMap.containsKey(cm)) {
                    if (unit == TimeUnit.DAILY) isActive = activityMap.get(cm).activeDays.contains(t);
                    else isActive = activityMap.get(cm).activeHours.contains(t);
                }
                double rate;
                switch (type.toLowerCase()) {
                    case "food": rate = getCalorieRate(cm, isActive); break;
                    case "water": rate = getWaterRate(cm, isActive); break;
                    case "oxygen": rate = getOxygenRate(cm, isActive); break;
                    default: rate = 0;
                }
                if (unit == TimeUnit.HOURLY) rate /= 24;
                data[t][i] = rate;
            }
        }

        return data;
    }

    public double[] getTotalResourceUsage(List<CrewMember> crew, TimeUnit unit, int duration, Map<CrewMember, ActivitySchedule> activityMap, String type) {
        double[][] perPersonUsage = getResourceOverTime(crew, unit, duration, activityMap, type);
        double[] totals = new double[perPersonUsage.length];
        for (int t = 0; t < perPersonUsage.length; t++) {
            double sum = 0;
            for (double val : perPersonUsage[t]) sum += val;
            totals[t] = sum;
        }
        return totals;
    }

    public double getTotalConsumption(List<CrewMember> crew, TimeUnit unit, int duration, Map<CrewMember, ActivitySchedule> activityMap, String type) {
        double[] usage = getTotalResourceUsage(crew, unit, duration, activityMap, type);
        double sum = 0;
        for (double v : usage) sum += v;
        return sum;
    }

    public double[] getFuelUsage(double initialFuel, double dailyRate, int missionLength) {
        double[] levels = new double[missionLength + 1];
        double current = initialFuel;
        for (int i = 0; i <= missionLength; i++) {
            levels[i] = Math.max(current, 0);
            current -= dailyRate;
        }
        return levels;
    }

    public double[] getCrewBasedSupplyLevels(double missionLength, double initialSupply, List<CrewMember> crew, String type) {
        Map<CrewMember, ActivitySchedule> noActivity = new HashMap<>();
        int duration = (int) Math.ceil(missionLength);
        return getTotalResourceUsage(crew, TimeUnit.DAILY, duration, noActivity, type);
    }

    public double[] getSupplyLevels(double missionLength, double initialSupply, double dailyConsumption) {
        int days = (int) Math.ceil(missionLength);
        double[] levels = new double[days + 1];
        double currentSupply = initialSupply;
        for (int day = 0; day <= days; day++) {
            levels[day] = Math.max(currentSupply, 0);
            currentSupply -= dailyConsumption;
        }
        return levels;
    }
} // end class

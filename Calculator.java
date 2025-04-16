import java.util.List;

public class Calculator {

    Calculator() {}

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

    public double getDailyFoodConsumption(CrewMember member) {
        // Placeholder: adjust by sex and weight
        double base = 3035;
        if (member.getSex().equalsIgnoreCase("female")) base *= 0.85;
        base += (member.getWeight() - 75) * 10; // +/-10 cal per kg from baseline 75kg
        return base;
    }

    public double getDailyWaterConsumption(CrewMember member) {
        // Placeholder: base 2.6L, slightly adjust by age and weight
        double base = 2.6;
        base += (member.getWeight() - 75) * 0.01; // +/-10mL per kg from 75kg
        base += (member.getAge() - 30) * 0.005; // +/-5mL per year from 30yo
        return base;
    }

    public double getDailyOxygenConsumption(CrewMember member) {
        // Placeholder: base 1.083 kg, adjust by sex and weight
        double base = 1.083;
        if (member.getSex().equalsIgnoreCase("female")) base *= 0.90;
        base += (member.getWeight() - 75) * 0.01;
        return base;
    }

    public double getTotalDailyConsumption(List<CrewMember> crew, String type) {
        double total = 0;
        for (CrewMember member : crew) {
            switch (type.toLowerCase()) {
                case "food": total += getDailyFoodConsumption(member); break;
                case "water": total += getDailyWaterConsumption(member); break;
                case "oxygen": total += getDailyOxygenConsumption(member); break;
            }
        }
        return total;
    }

    public double[] getCrewBasedSupplyLevels(double missionLength, double initialSupply, List<CrewMember> crew, String type) {
        double dailyConsumption = getTotalDailyConsumption(crew, type);
        return getSupplyLevels(missionLength, initialSupply, dailyConsumption);
    }

} // end class
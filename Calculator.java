/* Notes:
 * 2.5 L water per crew member per day - 90% of which is recycled per day
 * Each crew member needs 3035 kcal/day
 * User will enter hours/minutes per day resting/awake/exercizing
 * Oxygen consumption rates:
 *  Resting - 0.771 kg/person/day
 *  
 */

 public class Calculator {

    public Calculator() {}

    // Calculates total resource consumption for a mission
    public ResourceUsage calculateTotalUsage(Vehicle vehicle) {
        double missionLength = vehicle.getMissionLength();

        double totalCalories = 0;
        double totalWater = 0;
        double totalOxygen = 0;

        for (CrewMember cm : vehicle.vehicleCrew) {
            double kcalPerDay = estimateCalorieUse(cm);
            double waterPerDay = estimateWaterUse(cm);
            double oxygenPerDay = estimateOxygenUse(cm);

            totalCalories += kcalPerDay * missionLength;
            totalWater += waterPerDay * missionLength;
            totalOxygen += oxygenPerDay * missionLength;
        }

        return new ResourceUsage(totalCalories, totalWater, totalOxygen);
    }

    // Estimate calorie use (NASA average: ~3035 kcal/day)
    private double estimateCalorieUse(CrewMember cm) {
        // In future we could optionally adjust based on height, weight, sex
        return 3035; // kcal/day per crewmember
    }

    // Estimate water use (2.5 L/day = ~2.5 kg/day)
    private double estimateWaterUse(CrewMember cm) {
        return 2.5; // kg/day
    }

    // Estimate oxygen use (NASA source: ~1.083 kg/day with regular exercise)
    private double estimateOxygenUse(CrewMember cm) {
        return 1.083; // kg/day
    }

    // Determines whether any resource will deplete before mission ends
    public boolean isMissionFeasible(Vehicle vehicle, ResourceUsage required) {
        return required.totalCalories <= vehicle.getFood()
            && required.totalWater <= vehicle.getWater()
            && required.totalOxygen <= vehicle.getOx();
    }

    public static class ResourceUsage {
        public double totalCalories;
        public double totalWater;
        public double totalOxygen;

        public ResourceUsage(double totalCalories, double totalWater, double totalOxygen) {
            this.totalCalories = totalCalories;
            this.totalWater = totalWater;
            this.totalOxygen = totalOxygen;
        }

        //@Override
        public String toString() {
            return "Total Calories: " + totalCalories + " kcal\n" +
                   "Total Water: " + totalWater + " kg\n" +
                   "Total Oxygen: " + totalOxygen + " kg";
        }
    }
} 

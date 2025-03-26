
public class VehicleResources {
    private double foodSupply;
    private double fuelSupply;
    private double oxSupply;
    private double waterSup;

    public VehicleResources(double initialFood, double initialFuel, double initialOx, double initialWater) {
        this.foodSupply = initialFood;
        this.fuelSupply = initialFuel;
        this.oxSupply = initialOx;
        this.waterSup = initialWater;
    }

    public double getFoodSup() {
        return foodSupply;
    }

    public double getFuelSup() {
        return fuelSupply;
    }

    public double getOxSup() {
        return oxSupply;
    }
    
    public double getWaterSup(){
        return waterSup;
    }

    public void setFoodSup(double foodSupply) {
        if (foodSupply > 0) {
            this.foodSupply = foodSupply;
        } else {
            System.out.println("Invalid input: Food supply must be greater than 0.");
        }
    }

    public void setFuelSup(double fuelSupply) {
        if (fuelSupply > 0) {
            this.fuelSupply = fuelSupply;
        } else {
            System.out.println("Invalid input: Fuel supply must be greater than 0.");
        }
    }

    public void setOxSup(double oxSupply) {
        if (oxSupply > 0) {
            this.oxSupply = oxSupply;
        } else {
            System.out.println("Invalid input: Ox supply must be greater than 0.");
        }
    }

    public void setWaterSup(double waterSup){
        if (waterSup > 0) {
            this.waterSup = waterSup;
        } else {
            System.out.println("Invalid input: Water supply must be greater than 0.");
        }
    }
}

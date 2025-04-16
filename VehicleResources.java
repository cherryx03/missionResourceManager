
public class VehicleResources {
    private double foodSupply;
    private double fuelSupply;
    private double oxSupply;
    private double waterSupply;
    private double foodConsumeRate;
    private double fuelConsumeRate;
    private double oxConsumeRate;
    private double waterConsumeRate;
    private double oxReclaimRate;
    private double waterReclaimRate;

    public VehicleResources(double initialFood, double initialFuel,
                            double initialOx, double initialWater,
                            double oxReclaimRate, double waterReclaimRate) {
        this.foodSupply = initialFood;
        this.fuelSupply = initialFuel;
        this.oxSupply = initialOx;
        this.waterSupply = initialWater;
        this.oxReclaimRate = oxReclaimRate;
        this.waterReclaimRate = waterReclaimRate;
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
    
    public double getWaterSupply(){
        return waterSupply;
    }

    public double getOxReclaimRate() {
        return oxReclaimRate;
    }

    public double getWaterReclaimRate() {
        return waterReclaimRate;
    }

    public double getFoodConsumeRate() {
        return foodConsumeRate;
    }

    public double getFuelConsumeRate() {
        return fuelConsumeRate;
    }

    public double getOxConsumeRate() {
        return oxConsumeRate;
    }

    public double getWaterConsumeRate() {
        return waterConsumeRate;
    }

    public void setFoodSup(double foodSupply) {
        if (foodSupply > 0) {
            this.foodSupply = foodSupply;
        }
    }

    public void setFuelSup(double fuelSupply) {
        if (fuelSupply > 0) {
            this.fuelSupply = fuelSupply;
        }
    }

    public void setOxSup(double oxSupply) {
        if (oxSupply > 0) {
            this.oxSupply = oxSupply;
        }
    }

    public void setWaterSupply(double waterSupply){
        if (waterSupply > 0) {
            this.waterSupply = waterSupply;
        }
    }

    public void setOxReclaimRate(double oxReclaimRate) {
        if(oxReclaimRate > 0){
            this.oxReclaimRate = oxReclaimRate;
        }
    }

    public void setWaterReclaimRate(double waterReclaimRate) {
        if(waterReclaimRate>0){
            this.waterReclaimRate = waterReclaimRate;
        }
    }

    public void setFoodConsumeRate(double foodConsumeRate) {
        if(oxReclaimRate > 0){
            this.foodConsumeRate = foodConsumeRate;
        }
    }

    public void setFuelConsumeRate(double fuelConsumeRate) {
        if(oxReclaimRate > 0){
            this.fuelConsumeRate = fuelConsumeRate;
        }
    }

    public void setOxConsumeRate(double oxConsumeRate) {
        if(oxReclaimRate > 0){
            this.oxConsumeRate = oxConsumeRate;
        }
    }

    public void setWaterConsumeRate(double waterConsumeRate) {
        if(oxReclaimRate > 0){
            this.waterConsumeRate = waterConsumeRate;
        }
    }

    @Override
    public String toString() {
        return "Food Supply : " + getFoodSup() + "\n" +
                "Fuel Supply : " + getFuelSup() + "\n" +
                "Oxygen Supply : " + getOxSup() + "\n" +
                "Water Supply : " + getWaterSupply() + "\n" +
                "Oxygen Reclamation Rate : " + getOxReclaimRate() + "\n" +
                "Water Reclamation Rate : " + getWaterReclaimRate() + "\n";
    }
}

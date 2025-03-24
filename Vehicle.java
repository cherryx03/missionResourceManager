import java.util.ArrayList;

public class Vehicle {
    private int crewSize;
    private double missionLength;
    private VehicleResources vehicleRes;
    public ArrayList<CrewMember> vehicleCrew;

    public Vehicle(){
        this.crewSize = 0;
        this.vehicleCrew = new ArrayList<>();
        this.vehicleRes = new VehicleResources(0, 0, 0, 0);
    }

    public Vehicle(int crewSize, double initialFood, double initialFuel, double initialOx, double initialWater) {
        this.crewSize = crewSize;
        this.vehicleCrew = new ArrayList<>();
        this.vehicleRes = new VehicleResources(initialFood, initialFuel, initialOx, initialWater);
    }

    public int getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }

    public double getFood() {
        return vehicleRes.getFoodSup();
    }

    public double getWater() {
        return vehicleRes.getWaterSup();
    }

    public double getOx() {
        return vehicleRes.getOxSup();
    }

    public void setFood(int food) {
        vehicleRes.setFoodSup(food);
    }

    public void setWater(int water) {
        vehicleRes.setWaterSup(water);
    }

    public void setOx(int ox) {
        vehicleRes.setOxSup(ox);
    }
}

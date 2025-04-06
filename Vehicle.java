import java.util.ArrayList;

public class Vehicle {
    private int crewSize;
    private double missionLength;
    private VehicleResources vehicleRes;
    private ArrayList<CrewMember> vehicleCrew;

    Vehicle(){
        this.crewSize = 0;
        this.vehicleCrew = new ArrayList<>();
        this.vehicleRes = new VehicleResources(0, 0, 0, 0);
    }

    Vehicle(int crewSize, double missionLength, double initialFood, double initialFuel, double initialOx, double initialWater) {
        setCrewSize(crewSize);
        setMissionLength(missionLength);
        this.vehicleCrew = new ArrayList<>();
        this.vehicleRes = new VehicleResources(initialFood, initialFuel, initialOx, initialWater);
    }

    public boolean addCrewMember(CrewMember crewMember){
        return vehicleCrew.add(crewMember);
    }
    public boolean removeCrewMemberByObject(CrewMember crewMember){
        return vehicleCrew.remove(crewMember);
    }
    public CrewMember removeCrewMemberByIndex(int memberPos){
        return vehicleCrew.remove(memberPos);
    }

    public int getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }

    public double getMissionLength() {return missionLength;}

    private void setMissionLength(double missionLength) {
        this.missionLength = missionLength;
    }

    public double getFood() {
        return vehicleRes.getFoodSup();
    }

    public double getFuel() {
        return vehicleRes.getFuelSup();
    }

    public double getWater() {
        return vehicleRes.getWaterSup();
    }

    public double getOx() {
        return vehicleRes.getOxSup();
    }

    public void setFood(double food) {
        vehicleRes.setFoodSup(food);
    }

    public void setFuel(double fuel) {
        vehicleRes.setFuelSup(fuel);
    }

    public void setWater(double water) {
        vehicleRes.setWaterSup(water);
    }

    public void setOx(double ox) {
        vehicleRes.setOxSup(ox);
    }

    public String toString(){

        String returnString =
                    "Crew Size : " + getCrewSize() + "," +
                    "Mission Length : " + getMissionLength() + "," +
                    "Food Supply : " + getFood() + "," +
                    "Fuel Supply : " + getFuel() + "," +
                    "Oxygen Supply : " + getOx() + "," +
                    "Water Supply : " + getWater();

        int memberCount=1;
        for(CrewMember member:vehicleCrew){
            returnString += "," +
                    "Member " + memberCount + " Name : " + member.getName() + "," +
                    "Member " + memberCount + " Age : " + member.getAge() + "," +
                    "Member " + memberCount + " Sex : " + member.getSex() + "," +
                    "Member " + memberCount + " Height : " + member.getHeight() + "," +
                    "Member " + memberCount + " Weight : " + member.getWeight();
            memberCount++;
        }

        return returnString;
    }

}

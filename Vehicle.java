import java.util.ArrayList;

public class Vehicle {

    private int crewSize;
    private double missionLength;
    public VehicleResources vehicleRes;
    private ArrayList<CrewMember> vehicleCrew;

    private boolean valuesLoadedFromFile;

    Vehicle(){
        this.crewSize = 0;
        this.vehicleCrew = new ArrayList<>();
        this.vehicleRes = new VehicleResources(0, 0, 0, 0, 0, 0);
    }

    Vehicle(int crewSize, double missionLength, double initialFood, double initialFuel, 
            double initialOx, double initialWater, double oxReclaimRate, double waterReclaimRate) {
        setCrewSize(crewSize);
        setMissionLength(missionLength);
        this.vehicleCrew = new ArrayList<>();
        this.vehicleRes = new VehicleResources(initialFood, initialFuel, initialOx, initialWater, oxReclaimRate, waterReclaimRate);
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

    // getCrewMembers added by cw
    public ArrayList<CrewMember> getCrewMembers() {
        return vehicleCrew;
    }

    public int getCrewSize() {
        return crewSize;
    }

    public double getMissionLength() {return missionLength;}

    public boolean areValuesLoadedFromFile() {
        return valuesLoadedFromFile;
    }

    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }

    public void setMissionLength(double missionLength) {
        this.missionLength = missionLength;
    }

    public void setFood(double food) {
        vehicleRes.setFoodSupply(food);
    }

    public void setFuel(double fuel) {
        vehicleRes.setFuelSupply(fuel);
    }

    public void setWater(double water) {
        vehicleRes.setWaterSupply(water);
    }

    public void setOx(double ox) {
        vehicleRes.setOxSupply(ox);
    }

    public void setValuesLoadedFromFile(boolean valuesLoadedFromFile){this.valuesLoadedFromFile = valuesLoadedFromFile;}

    public String toString(){

        String returnString =
                    "Crew Size : " + getCrewSize() + "\n" +
                    "Mission Length : " + getMissionLength() + "\n" + vehicleRes.toString();
                            
                    
        int memberCount=1;
        for(CrewMember member:vehicleCrew){

            if(memberCount!=1){returnString+="\n";}

            returnString +=
                    "Member " + memberCount + " Name : " + member.getName() + "\n" +
                    "Member " + memberCount + " Age : " + member.getAge() + "\n" +
                    "Member " + memberCount + " Sex : " + member.getSex() + "\n" +
                    "Member " + memberCount + " Height : " + member.getHeight() + "\n" +
                    "Member " + memberCount + " Weight : " + member.getWeight() + "\n" +
                    "Member " + memberCount + " Exercise : " + member.getExercise();
            memberCount++;
        }

        return returnString;
    }

}

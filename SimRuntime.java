
import java.util.ArrayList;

public class SimRuntime {
    private GUIHandler gui;
    private FileHandler fileHandler;

    private Vehicle vehicle;
    public Calculator calculator;

    // Constructor
    SimRuntime(){

        vehicle = new Vehicle();
        calculator = new Calculator();
        fileHandler = new FileHandler();

    }

    // Core operation of program
    public void run(){

        boolean loadSuccess;// (for now!!)

        if(fileHandler.isFileGood()){
            System.out.println("Input file [" + FileHandler.getFileName() + "] found.");
            loadSuccess = true;//(vehicle);
        }else{
            loadSuccess = false;
        }

        //System.out.println("File Contents Retrieved : " + loadSuccess);

        gui = new GUIHandler(this, vehicle, loadSuccess, calculator);

        System.out.println("\nvehicle after gui");
        System.out.println(vehicle);
    }

    // Initial Population of Vehicle Using Input File
    boolean loadFiles(Vehicle vehicle){
        /**
             Required behavior:
             - Extract file contents
             - Populate Vehicle with file contents

             File Format:

             First: Vehicle
              1. int crewSize          [n]
              2. double missionLength  [days]

             Second: Resources
              3. double initialFood         [kcal]
              4. double initialFuel         [kg]
              5. double initialOx           [kg]
              6. double initialWater        [kg]
              7. double oxReclaimRate       [%]
              8. double WaterReclaimRate    [%]

             Third: Crew Members
              9. String memberName
              10. int memberAge            [n]
              11. String memberSex         [M/F]
              11. double memberHeight      [cm]
              12. double memberWeight      [kg]
              13. double memberExercise    [hr/day]
             Repeat Crew Members
         */

        ArrayList<String> fileContents = fileHandler.readFile();

//        System.out.println(fileContents);

        boolean loadSuccess = fileHandler.isFileGood();

//        for(String str : fileContents){
//            System.out.println(str);
//        }
        if(loadSuccess) {
            try {
                // Add file information to vehicle
                vehicle.setCrewSize(Integer.parseInt(fileContents.get(0).replace("Crew Size : ", "")));          // Crew Size
                vehicle.setMissionLength(Double.parseDouble(fileContents.get(1).replace("Mission Length : ", "")));   // Mission Length
                vehicle.setFood(Double.parseDouble(fileContents.get(2).replace("Food Supply : ", "")));            // Initial Food
                vehicle.setFuel(Double.parseDouble(fileContents.get(3).replace("Fuel Supply : ", "")));            // Initial Fuel
                vehicle.setOx(Double.parseDouble(fileContents.get(4).replace("Oxygen Supply : ", "")));              // Initial Ox
                vehicle.setWater(Double.parseDouble(fileContents.get(5).replace("Water Supply : ", "")));           // Initial Water
                vehicle.vehicleRes.setOxReclaimRate(Double.parseDouble(fileContents.get(6).replace("Oxygen Reclamation Rate : ", ""))); // Ox Reclaim Rate
                vehicle.vehicleRes.setWaterReclaimRate(Double.parseDouble(fileContents.get(7).replace("Water Reclamation Rate : ", ""))); // Water Reclaim Rate

                // Add all crew members present in file to the new vehicle
                boolean addSuccess;
                String memberTag;
                int memberNum = 0;
                for (int i = 8; i < fileContents.size(); i += 6) {
                    memberNum += 1;
                    memberTag = "Member " + memberNum;
                    addSuccess = vehicle.addCrewMember(
                            new CrewMember(
                                    fileContents.get(i).replace(memberTag + " Name : ", ""),
                                    Integer.parseInt(fileContents.get(i + 1).replace(memberTag + " Age : ", "")),
                                    fileContents.get(i + 2).replace(memberTag + " Sex : ", ""),
                                    Double.parseDouble(fileContents.get(i + 3).replace(memberTag + " Height : ", "")),
                                    Double.parseDouble(fileContents.get(i + 4).replace(memberTag + " Weight : ", "")),
                                    Double.parseDouble(fileContents.get(i + 5).replace(memberTag + " Exercise : ", ""))
                            )
                    );



                    loadSuccess = addSuccess;
                }

//            for(CrewMember crewMember : vehicle.getCrewMembers()){
//                System.out.println(crewMember);
//            }

                // Print contents for demo

//            for(String demoPrint : vehicle.toString().split("\n")){
//                System.out.println(demoPrint);
//            }

            } catch (NumberFormatException e) {
                System.out.println("---Invalid File Contents---");
                loadSuccess = false;
            }

        }

        return loadSuccess;
    }

    public void saveFile(Vehicle vehicle){

        String[] fileContents = vehicle.toString().split("\n");

        fileHandler.writeFile(fileContents);

    }

}

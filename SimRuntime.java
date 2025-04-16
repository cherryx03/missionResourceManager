
import java.util.ArrayList;

public class SimRuntime {

    private GUIHandler gui;
    private FileHandler fileHandler;

    private Vehicle vehicle;
    private Calculator calculator;

    // Constructor
    SimRuntime(){

        vehicle = new Vehicle();

        calculator = new Calculator();
        gui = new GUIHandler(vehicle);
        fileHandler = new FileHandler();

//        gui.GUISetup(vehicle);
//        gui.missionPanelSetup();

        // This is an empty shell; once data is available, a new vehicle is constructed with those values.


    }

    // Core operation of program
    public void run(){

        boolean loadSuccess;

        if(fileHandler.isFileGood()){
            System.out.println("Input file [" + FileHandler.getFileName() + "] found.");
            loadSuccess = loadFiles(fileHandler, vehicle);
        }else{
            loadSuccess = false;
        }

        System.out.println("File Contents Retrieved : " + loadSuccess);

        // The main flow of the program shall be populated after the auxiliary classes are fully determined

    }

    // Initial Population of Vehicle Using Input File
    private boolean loadFiles(FileHandler fileHandler, Vehicle vehicle){

        // This method shall be filled after the structure of the vehicle class and of the input file is determined
        // Required behavior:
        // - Extract file contents
        // - Populate Vehicle with file contents

        // File Format:

        // First: Vehicle
        // 1. int crewSize          [n]
        // 2. double missionLength  [days]

        // Second: Resources
        // 3. double initialFood         [kcal]
        // 4. double initialFuel         [kg]
        // 5. double initialOx           [kg]
        // 6. double initialWater        [kg]
        // 7. double oxReclaimRate       [%]
        // 8. double WaterReclaimRate    [%]

        // Third: Crew Members
        // 9. String memberName
        // 10. int memberAge         [n]
        // 11. String memberSex
        // 11. double memberHeight  [cm]
        // 12. double memberWeight  [kg]
        // Repeat Crew Members

        ArrayList<String> fileContents = fileHandler.readFile();

        try{

            // Add file information to vehicle
            vehicle.setCrewSize(Integer.parseInt(fileContents.get(0).replace("Crew Size : ","")));          // Crew Size
            vehicle.setMissionLength(Double.parseDouble(fileContents.get(1).replace("Mission Length : ","")));   // Mission Length
            vehicle.setFood(Double.parseDouble(fileContents.get(2).replace("Food Supply : ","")));            // Initial Food
            vehicle.setFuel(Double.parseDouble(fileContents.get(3).replace("Fuel Supply : ","")));            // Initial Fuel
            vehicle.setOx(Double.parseDouble(fileContents.get(4).replace("Oxygen Supply : ","")));              // Initial Ox
            vehicle.setWater(Double.parseDouble(fileContents.get(5).replace("Water Supply : ","")));           // Initial Water
            vehicle.vehicleRes.setOxReclaimRate(Double.parseDouble(fileContents.get(6).replace("Oxygen Reclamation Rate : ",""))); // Ox Reclaim Rate
            vehicle.vehicleRes.setWaterReclaimRate(Double.parseDouble(fileContents.get(7).replace("Water Reclamation Rate : ",""))); // Water Reclaim Rate

            // Add all crew members present in file to the new vehicle
            boolean addSuccess;
            String memberTag;
            for(int i = 6; i<fileContents.size(); i+=5){
                memberTag = "Member "+ i;
                addSuccess = vehicle.addCrewMember(new CrewMember(
                        fileContents.get(i).replace(memberTag+" Name : ",""),
                        Integer.parseInt(fileContents.get(i+1).replace(memberTag+" Age : ","")),
                        fileContents.get(i+2).replace(memberTag+" Sex : ",""),
                        Integer.parseInt(fileContents.get(i+3).replace(memberTag+" Height : ","")),
                        Integer.parseInt(fileContents.get(i+4).replace(memberTag+" Weight : ",""))
                        )
                );

                if(!addSuccess){return false;}
            }

            // Print contents for demo

            for(String demoPrint:vehicle.toString().split(",")){
                System.out.println(demoPrint);
            }

        }catch(NumberFormatException e){
            System.out.println("---Invalid File Contents---");
            return false;
        }

        return true;
    }

    private boolean saveFile(FileHandler fileHandler, Vehicle vehicle){

        String[] fileContents = vehicle.toString().split("\n");

        boolean success = fileHandler.writeFile(fileContents);

        return success;
    };

}


import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SimRuntime {

    private GUIHandler gui;
    private FileHandler fileHandler;

    private Vehicle vehicle;
    private Calculator calculator;

    // Constructor
    SimRuntime(){

        gui = new GUIHandler();
        fileHandler = new FileHandler();

        vehicle = new Vehicle();
        // This is an empty shell; once data is available, a new vehicle is constructed with those values.

        calculator = new Calculator();

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
        // 3. double initialFood    [kcal]
        // 4. double initialFuel    [kg]
        // 5. double initialOx      [kg]
        // 6. double initialWater   [kg]

        // Third: Crew Members
        // 7. String memberName
        // 8. int memberAge         [n]
        // 9. String memberSex
        // 10. double memberHeight  [cm]
        // 11. double memberWeight  [kg]
        // Repeat Crew Members

        ArrayList<String> fileContents = fileHandler.readFile();

        try{

            // Construct a new vehicle with file information
            vehicle = new Vehicle(
                    Integer.parseInt(fileContents.get(0)),      // Crew Size
                    Double.parseDouble(fileContents.get(1)),    // Mission Length
                    Double.parseDouble(fileContents.get(2)),    // Initial Food
                    Double.parseDouble(fileContents.get(3)),    // Initial Fuel
                    Double.parseDouble(fileContents.get(4)),    // Initial Ox
                    Double.parseDouble(fileContents.get(5))    // Initial Water
            );

            // Add all crew members present in file to the new vehicle
            for(int i = 6; i<fileContents.size(); i+=5){

                vehicle.addCrewMember(new CrewMember(
                        fileContents.get(i),
                        Integer.parseInt(fileContents.get(i+1)),
                        fileContents.get(i+2),
                        Integer.parseInt(fileContents.get(i+3)),
                        Integer.parseInt(fileContents.get(i+4))
                        )
                );

            }

            // Print contents for demo

            for(String demoPrint:vehicle.toString().split(",")){
                System.out.println(demoPrint);
            }

        }catch(NumberFormatException e){
            vehicle = new Vehicle();
            System.out.println("---Invalid File Contents---");
        }

        return true;
    }

}

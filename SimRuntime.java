import java.io.File;
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
            loadSuccess = loadFiles(fileHandler, vehicle);
        }else{
            loadSuccess = false;
        }

        // The main flow of the program shall be populated after the auxiliary classes are fully determined

    }

    // Initial Population of Vehicle Using Input File
    private boolean loadFiles(FileHandler fileHandler, Vehicle vehicle){

        // This method shall be populated after the structure of the vehicle class and of the input file is determined

        return true;
    }

}

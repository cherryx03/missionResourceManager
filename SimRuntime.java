import java.io.File;

public class SimRuntime {

    private GUIHandler gui;
    private FileHandler fileHandler;

    private Vehicle vehicle;
    private Calculator calculator;

    SimRuntime(){

        gui = new GUIHandler();
        fileHandler = new FileHandler();

        vehicle = new Vehicle();
        calculator = new Calculator();

    }

    public void run(){}

    private void loadFiles(FileHandler fileHandler, Vehicle vehicle){}

}

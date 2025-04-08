import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {

    private Vehicle vehicle;
    private Calculator calculator;
    private boolean firstSubmit;

    GUIHandler guiHandler;

    ActionHandler(Vehicle vehicle, GUIHandler guiHandler){
        this.vehicle = vehicle;
        this.guiHandler = guiHandler;

    }

    public void actionPerformed(ActionEvent e) {
        if(guiHandler.isFirstSubmit()){
            //guiHandler.memberPanelSetup(vehicle);
            guiHandler.takeFirstInputs(vehicle, calculator);
        }
        else{
            guiHandler.takeAllInputs(vehicle, calculator);
        }
    }


}

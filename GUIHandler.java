import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class GUIHandler {

    private JFrame frame;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel crewPanel;
    private JPanel missionPanel;

    private boolean firstSubmit;

    private JTextField fuelField;
    private JTextField oxygenField;
    private JTextField waterField;
    private JTextField lengthField;
    private JTextField foodField;
    private JTextField crewField;

    private JLabel fuelLabel;
    private JLabel oxygenLabel;
    private JLabel waterLabel;
    private JLabel lengthLabel;
    private JLabel foodLabel;
    private JLabel crewLabel;

    
    GUIHandler(Vehicle vehicle){

        frame = GUISetup(vehicle);

        // SHOW GUI (frame/window):
        frame.setVisible(true);

    } // this bracket closes GUIHandler(){...} constructor.
    // if it should not close here and should move to the end, do what you think is best
    // but if it breaks, put it back lol


    private JFrame GUISetup(Vehicle vehicle){
        // set up initial GUI parameters (size, general layout, etc.)
        JFrame frame = frameSetup();
        // setup mission and crew panels and put them into the leftPanel
        leftPanelSetup(frame);
        // create rightPanel in window -- this will hold the output plots later:
        rightPanelSetup(frame);
        // create submitButton at bottom of window (frame):
        submitButtonSetup(vehicle, frame);
        return frame;
    }

    private JFrame frameSetup(){
        JFrame frame = new JFrame("Mission Setup");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        return frame;

    }

    private JPanel missionPanelSetup(){

        missionPanel = new JPanel(new GridLayout(7, 2));

        // create labels and text fields to go in missionPanel, then add them:
        addFields(missionPanel, createMissionFields());
        addLabels(missionPanel, createMissionLabels());

        return missionPanel;
    }

    private JScrollPane crewPanelSetup(){
        // create crewPanel to hold crewmember details/inputs:
        crewPanel = new JPanel();
        crewPanel.setLayout(new BoxLayout(crewPanel, BoxLayout.Y_AXIS));

        // create scroll pane for scrollable crew input section:
        return new JScrollPane(crewPanel);
    }

    public void memberPanelSetup(Vehicle vehicle){

        if(isFirstSubmit()){
            crewPanel.removeAll();
            int crewSize = vehicle.getCrewSize();

            for(int i=0; i<crewSize; i++) {

                JPanel memberPanel = new JPanel();

                memberPanel.setLayout(new GridLayout(6, 2));
                memberPanel.setBorder(BorderFactory.createTitledBorder("Crewmember " + (i + 1)));

                addLabels(memberPanel, createMemberLabels());
                addFields(memberPanel, createMemberFields());

                crewPanel.add(memberPanel);
            }

            setFirstSubmit(true);
            frame.revalidate();
            frame.repaint();
        }
        else{

            int missionLength = Integer.parseInt(lengthField.getText());
            int crewSize = Integer.parseInt(crewField.getText());
            double initialFood = Double.parseDouble(foodField.getText());
            double initialWaterSupply = Double.parseDouble(waterField.getText());
            double initialOxygenSupply = Double.parseDouble(oxygenField.getText());
            double initialFuelSupply = Double.parseDouble(fuelField.getText());

            rightPanel.removeAll();
            rightPanel.add(createChartPanel("Food Supply", "Calories",
                    missionLength, initialFood, 3035 * crewSize, Color.GREEN));
            rightPanel.add(createChartPanel("Water Supply", "Liters",
                    missionLength, initialWaterSupply, 2.6 * crewSize, Color.BLUE));
            rightPanel.add(createChartPanel("Oxygen Supply", "kg",
                    missionLength, initialOxygenSupply, 7.581 * crewSize, Color.BLACK));
            rightPanel.add(createChartPanel("Fuel Supply", "kg",
                    missionLength, initialFuelSupply, 19, Color.RED));

            frame.revalidate();
            frame.repaint();
        }

    }

    private ChartPanel createChartPanel(String title, String yAxis, int missionLength, double initialSupply, double dailyConsumption, Color color) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double supply = initialSupply;
        for (int j = 0; j <= missionLength; j++) {
            dataset.addValue(supply, title, Integer.toString(j));
            supply -= dailyConsumption;
        }
        JFreeChart chart = ChartFactory.createLineChart(title, "Time [days]", yAxis, dataset);
        chart.getCategoryPlot().getRenderer().setSeriesPaint(0, color);
        return new ChartPanel(chart);
    }

    private void leftPanelSetup(JFrame frame){
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(480, 800));
        leftPanel.add(crewPanelSetup(), BorderLayout.CENTER);
        leftPanel.add(missionPanelSetup(), BorderLayout.NORTH);
        frame.add(leftPanel);
    }

    private void rightPanelSetup(JFrame frame){
        rightPanel = new JPanel(new GridLayout(2, 2));
        rightPanel.setPreferredSize(new Dimension(720, 800));
        frame.add(rightPanel);
    }

    private void submitButtonSetup(Vehicle vehicle, JFrame frame){
        JButton submitButton = new JButton("Submit");
        // Add an ActionListener to the button in the form of ActionHandler and have it do the thing
        // make sure it does the first submit task, not both simultaneously upon first hitting 'submit'
        submitButton.addActionListener(new ActionHandler(vehicle, this));
        //TODO: FIGURE OUT WHATS UP W THE GUI LISTENERS
        frame.add(submitButton);
    }

    public void addLabels(JPanel panel, JLabel[] labels){
        for(JLabel label : labels) {
            panel.add(label);
        }
    }

    public void addFields(JPanel panel, JTextField[] fields){
        for(JTextField field : fields){
            panel.add(field);
        }
    }

    private JLabel[] createMemberLabels(){

        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Age [years]:");
        JLabel heightLabel = new JLabel("Height [cm]:");
        JLabel weightLabel = new JLabel("Weight [kg]:");
        JLabel sexLabel = new JLabel("Sex [M, F, N/A]:");

        return new JLabel[]{nameLabel, ageLabel, heightLabel, weightLabel, sexLabel};

    }

    private JTextField[] createMemberFields(){

        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField heightField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField sexField = new JTextField();

        return new JTextField[]{nameField, ageField, heightField, weightField, sexField};
    }

    private JLabel[] createMissionLabels(){
        fuelLabel = new JLabel("Fuel Capacity [kg]:");
        oxygenLabel = new JLabel("Initial Oxygen [kg]:");
        waterLabel = new JLabel("Onboard Water Supply [L]:");
        lengthLabel = new JLabel("Mission Length [days]:");
        foodLabel = new JLabel("Onboard Food Supply [Calories]:");
        crewLabel = new JLabel("Crew Size [number of members]:");

        return new JLabel[]{fuelLabel, oxygenLabel, waterLabel, lengthLabel, foodLabel, crewLabel};

    }

    private JTextField[] createMissionFields(){
        fuelField = new JTextField();
        oxygenField = new JTextField();
        waterField = new JTextField();
        lengthField = new JTextField();
        foodField = new JTextField();
        crewField = new JTextField();

        return new JTextField[]{fuelField, oxygenField, waterField, lengthField, foodField, crewField};
    }

    public boolean isFirstSubmit() {
        return firstSubmit;
    }
    public void setFirstSubmit(boolean firstSubmit) {
        this.firstSubmit = firstSubmit;
    }


} // end public class GUIHandler {...}

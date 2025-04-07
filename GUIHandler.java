import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

    // private final Calculator calculator;

    public GUIHandler(Vehicle vehicle, Calculator calculator){
        this.firstSubmit = true;

        // Create GUI elements before making the frame visible
        frame = frameSetup();
        //leftPanelSetup(frame); // Sets up missionPanel immediately
        submitButtonSetup(vehicle, frame);

        frame.setVisible(true);
        this.firstSubmit = true;
        frame = GUISetup(vehicle);
        frame.setVisible(true);
    }

    public JFrame GUISetup(Vehicle vehicle){
        JFrame frame = frameSetup();
        JPanel leftPanel = leftPanelSetup(frame);
//        JPanel missionPanel = missionPanelSetup(leftPanel);
//        leftPanel.add(missionPanel);
        JPanel rightPanel = rightPanelSetup(frame);
        //submitButtonSetup(vehicle, frame);
        JButton submitButton = submitButtonSetup(vehicle, frame);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(submitButton, BorderLayout.SOUTH);
        return frame;
    }

    public JFrame frameSetup(){
        JFrame frame = new JFrame("Mission Setup");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        return frame;
    }

    public JPanel missionPanelSetup(JPanel leftPanel){
        missionPanel = new JPanel(new GridLayout(3, 2));
        missionPanel.add(fuelLabel); missionPanel.add(fuelField);
        missionPanel.add(oxygenLabel); missionPanel.add(oxygenField);
        missionPanel.add(waterLabel); missionPanel.add(waterField);
        missionPanel.add(lengthLabel); missionPanel.add(lengthField);
        missionPanel.add(foodLabel); missionPanel.add(foodField);
        missionPanel.add(crewLabel); missionPanel.add(crewField);
        //addFields(missionPanel, createMissionFields());
        //addLabels(missionPanel, createMissionLabels());
        //leftPanel.add(missionPanel);
        return missionPanel;
    }

    private JScrollPane crewPanelSetup(){
        crewPanel = new JPanel();
        crewPanel.setLayout(new BoxLayout(crewPanel, BoxLayout.Y_AXIS));
        return new JScrollPane(crewPanel);
    }

    public void memberPanelSetup(Vehicle vehicle){
        if(isFirstSubmit()){
            crewPanel.removeAll();
            int crewSize = Integer.parseInt(crewField.getText());
            vehicle.setCrewSize(crewSize);

            for(int i=0; i<crewSize; i++) {
                JPanel memberPanel = new JPanel();
                memberPanel.setLayout(new GridLayout(6, 2));
                memberPanel.setBorder(BorderFactory.createTitledBorder("Crewmember " + (i + 1)));
                addLabels(memberPanel, createMemberLabels());
                addFields(memberPanel, createMemberFields());
                crewPanel.add(memberPanel);
            }

            setFirstSubmit(false);
            frame.revalidate();
            frame.repaint();
        }
    }


private JPanel leftPanelSetup(JFrame frame){
    leftPanel = new JPanel();
    leftPanel.setLayout(new BorderLayout());
    leftPanel.setPreferredSize(new Dimension(480, 800));
    //leftPanel.add(crewPanelSetup(), BorderLayout.CENTER);
    leftPanel.add(missionPanelSetup(leftPanel), BorderLayout.NORTH);
    return leftPanel;
}

private JPanel rightPanelSetup(JFrame frame){
    rightPanel = new JPanel(new GridLayout(2, 2));
    rightPanel.setPreferredSize(new Dimension(720, 800));
    return rightPanel;
}

private JButton submitButtonSetup(Vehicle vehicle, JFrame frame){
    JButton submitButton = new JButton("Submit");
    submitButton.addActionListener(new ActionListener() {
    // submitButton.addActionListener(new ActionHandler(vehicle, this));
        boolean secondSubmit = false;
    // frame.add(submitButton, BorderLayout.SOUTH);
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!secondSubmit) {
                int crewSize = Integer.parseInt(crewField.getText());
                crewPanel.removeAll();
                for (int i = 0; i < crewSize; i++) {

                    memberPanelSetup(vehicle);
                }
                secondSubmit = true;
                frame.revalidate();
                frame.repaint();
            } else {
                //TODO: take all inputs
                // call calculator
                // create charts

                frame.revalidate();
                frame.repaint();
            }
        }
    });
return submitButton;
    }

public void takeFirstInputs(Vehicle vehicle, Calculator calculator) {
    // Parse mission-level inputs
    vehicle.setMissionLength(Double.parseDouble(lengthField.getText()));
    vehicle.setCrewSize(Integer.parseInt(crewField.getText()));
    vehicle.setFood(Double.parseDouble(foodField.getText()));
    vehicle.setWater(Double.parseDouble(waterField.getText()));
    vehicle.setOx(Double.parseDouble(oxygenField.getText()));
    vehicle.setFuel(Double.parseDouble(fuelField.getText()));
    // Clear existing crew data
    //for (int i = vehicle.getCrewMembers().size() - 1; i >= 0; i--) {
    //    vehicle.removeCrewMemberByIndex(i);
    //}
    memberPanelSetup(vehicle);
}

public void takeAllInputs(Vehicle vehicle, Calculator calculator){
    // Populate crew members from input panels
    for (Component comp : crewPanel.getComponents()) {
        if (comp instanceof JPanel) {
            JPanel memberPanel = (JPanel) comp;
            JTextField nameField = (JTextField) memberPanel.getComponent(1);
            JTextField ageField = (JTextField) memberPanel.getComponent(3);
            JTextField heightField = (JTextField) memberPanel.getComponent(5);
            JTextField weightField = (JTextField) memberPanel.getComponent(7);
            JTextField sexField = (JTextField) memberPanel.getComponent(9);

            CrewMember member = new CrewMember(
                    nameField.getText(),
                    Integer.parseInt(ageField.getText()),
                    sexField.getText(),
                    Double.parseDouble(heightField.getText()),
                    Double.parseDouble(weightField.getText())
            );

            vehicle.addCrewMember(member);
        }
    }

    // Proceed to calculate and display updated charts
    updateCharts(vehicle, calculator);
}

private void updateCharts(Vehicle vehicle, Calculator calculator){
    List<CrewMember> crew = vehicle.getCrewMembers();
    double missionLength = vehicle.getMissionLength();

    rightPanel.removeAll();
    rightPanel.add(createChartPanel("Food Supply", "Calories",
            calculator.getCrewBasedSupplyLevels(missionLength, vehicle.getFood(), crew, "food"), Color.GREEN));
    rightPanel.add(createChartPanel("Water Supply", "Liters",
            calculator.getCrewBasedSupplyLevels(missionLength, vehicle.getWater(), crew, "water"), Color.BLUE));
    rightPanel.add(createChartPanel("Oxygen Supply", "kg",
            calculator.getCrewBasedSupplyLevels(missionLength, vehicle.getOx(), crew, "oxygen"), Color.BLACK));
    rightPanel.add(createChartPanel("Fuel Supply", "kg",
            calculator.getSupplyLevels(missionLength, vehicle.getFuel(), 19), Color.RED));

    frame.revalidate();
    frame.repaint();
}

private ChartPanel createChartPanel(String title, String yAxis, double[] values, Color color) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for (int j = 0; j < values.length; j++) {
        dataset.addValue(values[j], title, Integer.toString(j));
    }
    JFreeChart chart = ChartFactory.createLineChart(title, "Time [days]", yAxis, dataset);
    chart.getCategoryPlot().getRenderer().setSeriesPaint(0, color);
    return new ChartPanel(chart);
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
    return new JLabel[]{
            new JLabel("Name:"),
            new JLabel("Age [years]"),
            new JLabel("Height [cm]"),
            new JLabel("Weight [kg]"),
            new JLabel("Sex [M, F, N/A]:")
    };
}

private JTextField[] createMemberFields(){
    return new JTextField[]{
            new JTextField(),
            new JTextField(),
            new JTextField(),
            new JTextField(),
            new JTextField()
    };
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
}
 // end class

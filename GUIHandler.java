import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GUIHandler {

    private JFrame frame;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel crewPanel;
    private JPanel missionPanel;

    private boolean firstSubmit;
    private boolean loadSuccess;

    private JTextField fuelField;
    private JTextField oxygenField;
    private JTextField waterField;
    private JTextField lengthField;
    private JTextField foodField;
    private JTextField crewField;
    private JTextField airRecField;
    private JTextField watRecField;

    private JLabel fuelLabel;
    private JLabel oxygenLabel;
    private JLabel waterLabel;
    private JLabel lengthLabel;
    private JLabel foodLabel;
    private JLabel crewLabel;
    private JLabel airRecLabel;
    private JLabel watRecLabel;

    GUIHandler(SimRuntime runtime, Vehicle vehicle, boolean loadSuccess){
        this.firstSubmit = true;
        this.loadSuccess = loadSuccess;

        // Create GUI elements before making the frame visible
        frame = frameSetup(runtime, vehicle);
        //leftPanelSetup(frame); // Sets up missionPanel immediately
        //submitButtonSetup(vehicle, frame);

//        frame.setVisible(true);
        //this.firstSubmit = true;
        frame = GUISetup(vehicle);
        frame.setVisible(true);
    }

    private JFrame frameSetup(SimRuntime runtime, Vehicle vehicle){
        JFrame frame = new JFrame("Mission Setup");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                runtime.saveFile(vehicle);
                frame.dispose();
            }
        });

        return frame;
    }

    private JFrame GUISetup(Vehicle vehicle){

        JPanel leftPanel = leftPanelSetup(vehicle);
//        JPanel missionPanel = missionPanelSetup(leftPanel);
//        leftPanel.add(missionPanel);
        JPanel rightPanel = rightPanelSetup();
        //submitButtonSetup(vehicle, frame);
        JButton submitButton = submitButtonSetup(vehicle, frame);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(submitButton, BorderLayout.SOUTH);
        return frame;
    }

    private JPanel leftPanelSetup(Vehicle vehicle){
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(480, 800));
        //leftPanel.add(crewPanelSetup(), BorderLayout.CENTER);
        leftPanel.add(missionPanelSetup(leftPanel, vehicle), BorderLayout.NORTH);
        return leftPanel;
    }

    private JPanel missionPanelSetup(JPanel leftPanel, Vehicle vehicle){

//        missionPanel = new JPanel(new GridLayout(7, 2));
//        new JTextField(fuelField, oxygenField, waterField, lengthField, foodField, crewField) = createMissionFields();

        missionPanel = new JPanel(new GridLayout(8, 2));

        JTextField[] missionFields = createMissionFields(vehicle);
        JLabel[] missionLabels = createMissionLabels();

        addLabelsWithFields(missionPanel, missionLabels, missionFields);

        leftPanel.add(missionPanel);

        return missionPanel;
    }

    private JPanel rightPanelSetup(){
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
                System.out.println("button pressed");
                if (!secondSubmit) {
                    System.out.println("first time");
                    //int crewSize = Integer.parseInt(crewField.getText());
                    //crewPanel.removeAll();
                    //for (int i = 0; i < crewSize; i++) {
                    takeFirstInputs(vehicle);
//                    System.out.println(vehicle);
                    leftPanel.add(crewPanelSetup(), BorderLayout.CENTER);
                    crewPanel = memberPanelSetup(vehicle);
                    //crewPanel.add(memberPanel);
                    //}
                    //frame.add(crewPanel);
                    crewPanel.revalidate();

//                    secondSubmit = true;
//                    frame.revalidate();
//                    frame.repaint();
                    secondSubmit = true;
                } else {
                    System.out.println("second(+) time");
                    //TODO: take all inputs
                    // call calculator
                    // create charts
                    takeCrewInputs(vehicle);
                    frame.revalidate();
                }
                frame.revalidate();
                frame.repaint();
            }
        });
        return submitButton;
    }

    private JPanel memberPanelSetup(Vehicle vehicle){
//        if(isFirstSubmit()){
//            crewPanel.removeAll();
        int crewSize = Integer.parseInt(crewField.getText());
        vehicle.setCrewSize(crewSize);
        System.out.println("crewsize set.");

//            for(int i=0; i<crewSize; i++) {
//                JPanel memberPanel = new JPanel();
//                memberPanel.setLayout(new GridLayout(6, 2));
//                memberPanel.setBorder(BorderFactory.createTitledBorder("Crewmember " + (i + 1)));
//                addLabels(memberPanel, createMemberLabels());
//                addFields(memberPanel, createMemberFields());
//                crewPanel.add(memberPanel);
//            }

        for (int i = 0; i < crewSize; i++) {
//            System.out.println("crewmember num. " + (i+1));
            JPanel memberPanel = new JPanel();
            memberPanel.setLayout(new GridLayout(6, 2));
            memberPanel.setBorder(BorderFactory.createTitledBorder("Crewmember " + (i + 1)));

            JLabel[] memberLabels = createMemberLabels();
            JTextField[] memberFields = createMemberFields(vehicle, i);

            addLabelsWithFields(memberPanel, memberLabels, memberFields);

            crewPanel.add(memberPanel);
            System.out.println("individual panel added");
        }
        System.out.println("crewPanel being passed back...");
        return crewPanel;
        //frame.revalidate();
        //frame.repaint();
    }

    private JScrollPane crewPanelSetup(){
        crewPanel = new JPanel();
        crewPanel.setLayout(new BoxLayout(crewPanel, BoxLayout.Y_AXIS));
        return new JScrollPane(crewPanel);
    }

    private void takeFirstInputs(Vehicle vehicle) {
        // Parse mission-level inputs
        vehicle.setMissionLength(Double.parseDouble(lengthField.getText()));
        vehicle.setCrewSize(Integer.parseInt(crewField.getText()));
        vehicle.vehicleRes.setFoodSupply(Double.parseDouble(foodField.getText()));
        vehicle.vehicleRes.setWaterSupply(Double.parseDouble(waterField.getText()));
        vehicle.vehicleRes.setOxSupply(Double.parseDouble(oxygenField.getText()));
        vehicle.vehicleRes.setFuelSupply(Double.parseDouble(fuelField.getText()));
        // Clear existing crew data
        //for (int i = vehicle.getCrewMembers().size() - 1; i >= 0; i--) {
        //    vehicle.removeCrewMemberByIndex(i);
        //}
    }

    private void takeCrewInputs(Vehicle vehicle){
        // Populate crew members from input panels
        for (Component comp : crewPanel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel memberPanel = (JPanel) comp;
                JTextField nameField = (JTextField) memberPanel.getComponent(1);
                JTextField ageField = (JTextField) memberPanel.getComponent(3);
                JTextField heightField = (JTextField) memberPanel.getComponent(5);
                JTextField weightField = (JTextField) memberPanel.getComponent(7);
                JTextField sexField = (JTextField) memberPanel.getComponent(9);
                JTextField exerciseField = (JTextField) memberPanel.getComponent(11);

                CrewMember member = new CrewMember(
                        nameField.getText(),
                        Integer.parseInt(ageField.getText()),
                        sexField.getText(),
                        Double.parseDouble(heightField.getText()),
                        Double.parseDouble(weightField.getText()),
                        Double.parseDouble(exerciseField.getText())
                );

                vehicle.addCrewMember(member);
            }
        }

//        // Proceed to calculate and display updated charts
//        updateCharts(vehicle, calculator);
    }

    public void updateCharts(Vehicle vehicle, Calculator calculator){
        List<CrewMember> crew = vehicle.getCrewMembers();
        double missionLength = vehicle.getMissionLength();

        rightPanel.removeAll();
        rightPanel.add(createChartPanel("Food Supply", "Calories", calculator.getCrewBasedSupplyLevels(missionLength, vehicle.vehicleRes.getFoodSupply(), crew, "food"), Color.GREEN));
        rightPanel.add(createChartPanel("Water Supply", "Liters", calculator.getCrewBasedSupplyLevels(missionLength, vehicle.vehicleRes.getWaterSupply(), crew, "water"), Color.BLUE));
        rightPanel.add(createChartPanel("Oxygen Supply", "kg", calculator.getCrewBasedSupplyLevels(missionLength, vehicle.vehicleRes.getOxSupply(), crew, "oxygen"), Color.BLACK));
        rightPanel.add(createChartPanel("Fuel Supply", "kg", calculator.getSupplyLevels(missionLength, vehicle.vehicleRes.getFuelSupply(), 19), Color.RED));

        frame.revalidate();
        frame.repaint();
    }

    private Component createChartPanel(String title, String yAxis, double[] values, Color color) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int j = 0; j < values.length; j++) {
            dataset.addValue(values[j], title, Integer.toString(j));
        }
        JFreeChart chart = ChartFactory.createLineChart(title, "Time [days]", yAxis, dataset);
        chart.getCategoryPlot().getRenderer().setSeriesPaint(0, color);
        return new ChartPanel(chart);
    }

    private void addLabelsWithFields(JPanel panel, JLabel[] labels, JTextField[] fields){

        for(int i = 0; i<labels.length; i++) {
            panel.add(labels[i]);
            panel.add(fields[i]);
        }
    }

    private JLabel[] createMemberLabels(){
        return new JLabel[]{
                new JLabel("Name:"),
                new JLabel("Age [years]"),
                new JLabel("Height [cm]"),
                new JLabel("Weight [kg]"),
                new JLabel("Sex [M, F, N/A]:"),
                new JLabel("Exercise [hrs/day]:")
        };
    }

    private JTextField[] createMemberFields(Vehicle vehicle, int memberIndex){

        JTextField memberName = new JTextField();
        JTextField memberAge = new JTextField();
        JTextField memberHeight = new JTextField();
        JTextField memberWeight = new JTextField();
        JTextField memberSex = new JTextField();
        JTextField memberExercise = new JTextField();

        if(loadSuccess && memberIndex<vehicle.getCrewSize()){

            CrewMember member = vehicle.getCrewMembers().get(memberIndex);
            memberName.setText(member.getName());
            memberAge.setText(String.valueOf(member.getAge()));
            memberHeight.setText(String.valueOf(member.getHeight()));
            memberWeight.setText(String.valueOf(member.getWeight()));
            memberSex.setText(member.getSex());
            memberExercise.setText(String.valueOf(member.getExercise()));

        }

        return new JTextField[]{memberName, memberAge, memberHeight, memberWeight, memberSex, memberExercise};

    }

    private JLabel[] createMissionLabels(){

        crewLabel = new JLabel("Crew Size [number of members]:");
        lengthLabel = new JLabel("Mission Length [days]:");
        fuelLabel = new JLabel("Fuel Capacity [kg]:");
        oxygenLabel = new JLabel("Initial Oxygen [kg]:");
        waterLabel = new JLabel("Onboard Water Supply [L]:");
        foodLabel = new JLabel("Onboard Food Supply [Calories]:");
        airRecLabel = new JLabel("Air Reclamation Rate [%]:");
        watRecLabel = new JLabel("Water Reclamation Rate [%]");

        return new JLabel[]{crewLabel, lengthLabel, fuelLabel, oxygenLabel, waterLabel, foodLabel, airRecLabel, watRecLabel};
    }

    private JTextField[] createMissionFields(Vehicle vehicle){

        crewField = new JTextField();
        lengthField = new JTextField();
        fuelField = new JTextField();
        oxygenField = new JTextField();
        waterField = new JTextField();
        foodField = new JTextField();
        airRecField = new JTextField();
        watRecField = new JTextField();

        if(loadSuccess) {
//            System.out.println(vehicle);
            crewField.setText(String.valueOf(vehicle.getCrewSize()));
            lengthField.setText(String.valueOf(vehicle.getMissionLength()));
            fuelField.setText(String.valueOf(vehicle.vehicleRes.getFuelSupply()));
            oxygenField.setText(String.valueOf(vehicle.vehicleRes.getOxSupply()));
            waterField.setText(String.valueOf(vehicle.vehicleRes.getWaterSupply()));
            foodField.setText(String.valueOf(vehicle.vehicleRes.getFoodSupply()));
            airRecField.setText(String.valueOf(vehicle.vehicleRes.getOxReclaimRate()));
            watRecField.setText(String.valueOf(vehicle.vehicleRes.getWaterReclaimRate()));
        }
        return new JTextField[]{crewField, lengthField, fuelField, oxygenField, waterField, foodField, airRecField, watRecField};
    }

//    private boolean isFirstSubmit() {
//        return firstSubmit;
//    }
//    private void setFirstSubmit(boolean firstSubmit) {
//        this.firstSubmit = firstSubmit;
//    }

}
 // end class

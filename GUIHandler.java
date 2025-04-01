import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIHandler {

    JFrame frame; // creates variable for frame (which is the GUI window!)
    GUIHandler(){
            // set up initial GUI parameters (size, general layout, etc.)
            frame = new JFrame("Mission Setup");
            frame.setSize(1200, 800);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // this leftPanel is the left part (1/3) of the window, where inputs will be
                // rest of window is reserved for outputs/plots!
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BorderLayout());
            leftPanel.setPreferredSize(new Dimension(480, 800));

            // create panel w/in leftPanel for vehicle details (first thing that pops up in window)
            JPanel missionPanel = new JPanel(new GridLayout(7, 2));

            // create labels and text fields to go in missionPanel:
            JLabel fuelLabel = new JLabel("Fuel Capacity [kg]:");
            JTextField fuelField = new JTextField();
            JLabel oxygenLabel = new JLabel("Initial Oxygen [kg]:");
            JTextField oxygenField = new JTextField();
            JLabel waterLabel = new JLabel("Onboard Water Supply [L]:");
            JTextField waterField = new JTextField();
            JLabel lengthLabel = new JLabel("Mission Length [days]:");
            JTextField lengthField = new JTextField();
            JLabel foodLabel = new JLabel("Onboard Food Supply [Calories]:");
            JTextField foodField = new JTextField();
            JLabel crewLabel = new JLabel("Crew Size [number of members]:");
            JTextField crewField = new JTextField();

            // we just created labels/txt fields above, now add them to the missionPanel:
            missionPanel.add(fuelLabel); missionPanel.add(fuelField);
            missionPanel.add(oxygenLabel); missionPanel.add(oxygenField);
            missionPanel.add(waterLabel); missionPanel.add(waterField);
            missionPanel.add(lengthLabel); missionPanel.add(lengthField);
            missionPanel.add(foodLabel); missionPanel.add(foodField);
            missionPanel.add(crewLabel); missionPanel.add(crewField);

            // add missionPanel to top of leftPanel
            leftPanel.add(missionPanel, BorderLayout.NORTH);

            // create crewPanel to hold crewmember details/inputs:
            JPanel crewPanel = new JPanel();
            crewPanel.setLayout(new BoxLayout(crewPanel, BoxLayout.Y_AXIS));

            // create scroll pane for scrollable crew input section:
            JScrollPane crewScrollPane = new JScrollPane(crewPanel);

            // add crewScrollPane to leftPanel, right under the missionPanel:
            leftPanel.add(crewScrollPane, BorderLayout.CENTER);

            // create rightPanel in window -- this will hold the output plots later:
            JPanel rightPanel = new JPanel(new GridLayout(2, 2));
            rightPanel.setPreferredSize(new Dimension(720, 800));

            // create submitButton at bottom of window (frame):
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                boolean secondSubmit = false; // make sure it does the first submit task, not both simultaneously upon first hitting 'submit'

                //TODO: FIGURE OUT WHATS UP W THE GUI LISTENERS
            });

            // add all previously created elements to frame:
            frame.add(leftPanel, BorderLayout.WEST);
            frame.add(rightPanel, BorderLayout.EAST);
            frame.add(submitButton, BorderLayout.SOUTH);

            // SHOW GUI (frame/window):
            frame.setVisible(true);
        } // this bracket closes GUIHandler(){...} constructor. 
            // if it should not close here and should move to the end, do what you think is best
            // but if it breaks, put it back lol


    // i dont remember why "@Override" was here below, but it worked w/ it, so... if it don't work, uncomment:
        // @Override

        // callback/action time!! yippie :D
public void actionPerformed(ActionEvent e) {
    if (!secondSubmit) {
        // if this is the first time user hits 'submit', take in crewSize variable from
        // missionPanel input:
        int crewSize = Integer.parseInt(crewField.getText());
        crewPanel.removeAll(); // clear crewPanel just in case anything is already there (I was taught it's good practice)
        for (int i = 0; i < crewSize; i++) {
            // for every member, create new panel memberPanel:
            JPanel memberPanel = new JPanel();
            memberPanel.setLayout(new GridLayout(6, 2));
            memberPanel.setBorder(BorderFactory.createTitledBorder("Crewmember " + (i + 1)));

            // create text fields and labels for memberPanel:
            JLabel nameLabel = new JLabel("Name:");
            JTextField nameField = new JTextField();
            JLabel ageLabel = new JLabel("Age [years]:");
            JTextField ageField = new JTextField();
            JLabel heightLabel = new JLabel("Height [cm]:");
            JTextField heightField = new JTextField();
            JLabel weightLabel = new JLabel("Weight [kg]:");
            JTextField weightField = new JTextField();
            JLabel sexLabel = new JLabel("Sex [M, F, N/A]:");
            JTextField sexField = new JTextField();

            // add components we just made to the panel:
            memberPanel.add(nameLabel); memberPanel.add(nameField);
            memberPanel.add(ageLabel); memberPanel.add(ageField);
            memberPanel.add(heightLabel); memberPanel.add(heightField);
            memberPanel.add(weightLabel); memberPanel.add(weightField);
            memberPanel.add(sexLabel); memberPanel.add(sexField);

            // add crewPanel to the memberPanel (container panel w/ scroll enabled):
            crewPanel.add(memberPanel);
        }
        secondSubmit = true; // set true here after creating all crew member panels
                             // so that next time user hits 'submit', we take in the crew
                             // member inputs and pass everything to the calculator!

        // check that frame is all chillin
        frame.revalidate();

        // "repaint" or update frame w/ new crewPanels in the memberPanel
        frame.repaint();

    } else { // if it IS second submit:
        // collect inputs!
        int missionLength = Integer.parseInt(lengthField.getText());
        int crewSize = Integer.parseInt(crewField.getText());
        double initialFood = Double.parseDouble(foodField.getText());
        double initialWaterSupply = Double.parseDouble(waterField.getText());
        double initialOxygenSupply = Double.parseDouble(oxygenField.getText());
        double initialFuelSupply = Double.parseDouble(fuelField.getText());


        // this is the function that creates the chart panels
        // it is called once for each plot to be made (below fcn).
        // TODO: currently, it also does the (rough) calculations. this needs to change!!!!
        // not sure why "String" is causing problems when declaring inputs & types. has red underlines in my IDE, but I might be having an idiot moment
        private static ChartPanel createChartPanel(String title, String yAxis, int missionLength, double initialSupply, double dailyConsumption, Color color) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            // TODO: remove the following 4 lines. The parameters passed in to this fcn need to go
            // TODO:     to the calculator.
            double supply = initialSupply;
            for (int j = 0; j <= missionLength; j++) {
                dataset.addValue(supply, title, Integer.toString(j));
                supply -= dailyConsumption;
            }
            // the following 3 lines should STAY in this function. they are responsible for creating the plots themselves.
            JFreeChart chart = ChartFactory.createLineChart(title, "Time [days]", yAxis, dataset);
            chart.getCategoryPlot().getRenderer().setSeriesPaint(0, color);
            // this returns the plot (chart)
            return new ChartPanel(chart);
        } // end fcn


        // make sure rightPanel is clear (good practice):
        rightPanel.removeAll();

        // call fcn createChartPanel (declared above) for each plot needed!
        // simultaneously adds each plot to rightPanel after recieving the plots from createChartPanel:
        rightPanel.add(createChartPanel("Food Supply", "Calories", missionLength, initialFood, 3035 * crewSize, Color.GREEN));
        rightPanel.add(createChartPanel("Water Supply", "Liters", missionLength, initialWaterSupply, 2.6 * crewSize, Color.BLUE));
        rightPanel.add(createChartPanel("Oxygen Supply", "kg", missionLength, initialOxygenSupply, 7.581 * crewSize, Color.BLACK));
        rightPanel.add(createChartPanel("Fuel Supply", "kg", missionLength, initialFuelSupply, 19, Color.RED));

        // now that we have all the plots, revalidate and repaint frame (window) to make visible:
        frame.revalidate();
        frame.repaint();
    } // end 'else'
    // (and pretend i didn't create a function in the middle of an if/else statement <3 )
} // end public void actionPreformed(ActionEvent e) {...}
} // end public class GUIHandler {...}

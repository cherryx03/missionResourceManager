import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class CrewMember {
    String name;
    double weight;
    double age;
    double height;
    String gender;

    public CrewMember(String name, double weight, double age, double height, String gender) {
        this.name = name;
        this.weight = weight;
        this.age = age;
        this.height = height;
        this.gender = gender;
    }

    public double OConsumption(int missionLength) {
        double InitialO = 10.0;
        for (int i = 1; i <= missionLength; i++) {
            InitialO -= 1.0;
        }
        return InitialO;
    }

    public double FoodConsumption(int missionLength) {
        double Consumed = 0.0;
        for (int i = 1; i <= missionLength; i++) {
            Consumed += 1.0;
        }
        return Consumed;
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", age=" + age +
                ", height=" + height +
                ", gender='" + gender + '\'' +
                '}';
    }
}

class MissionVehicle {
    double fuelCapacity;
    double startingOxygen;
    double missionLength;
    double food;
    int crewSize;
    ArrayList<CrewMember> crewMembers;

    public MissionVehicle(double fuelCapacity, double startingOxygen, double missionLength, double food, int crewSize) {
        this.fuelCapacity = fuelCapacity;
        this.startingOxygen = startingOxygen;
        this.missionLength = missionLength;
        this.food = food;
        this.crewSize = crewSize;
        this.crewMembers = new ArrayList<>();
    }

    public void addCrewMember(CrewMember crewMember) {
        if (crewMembers.size() < crewSize) {
            crewMembers.add(crewMember);
        }
    }

    public double FuelUse() {
        double CurrentFuel = fuelCapacity;
        for (int day = 1; day <= missionLength; day++) {
            CurrentFuel /= 1.5;
        }
        return CurrentFuel;
    }

    @Override
    public String toString() {
        return "MissionVehicle{" +
                "fuelCapacity=" + fuelCapacity +
                ", startingOxygen=" + startingOxygen +
                ", missionLength=" + missionLength +
                ", food=" + food +
                ", crewSize=" + crewSize +
                ", crewMembers=" + crewMembers +
                '}';
    }
}

public class MissionSetupGUI2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mission Setup");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel vehiclePanel = new JPanel(new GridLayout(6, 2));
        JLabel fuelLabel = new JLabel("Fuel Capacity:");
        JTextField fuelField = new JTextField();
        JLabel oxygenLabel = new JLabel("Starting Oxygen:");
        JTextField oxygenField = new JTextField();
        JLabel lengthLabel = new JLabel("Mission Length:");
        JTextField lengthField = new JTextField();
        JLabel foodLabel = new JLabel("Food Supply:");
        JTextField foodField = new JTextField();
        JLabel crewLabel = new JLabel("Crew Size:");
        JTextField crewField = new JTextField();
        vehiclePanel.add(fuelLabel); vehiclePanel.add(fuelField);
        vehiclePanel.add(oxygenLabel); vehiclePanel.add(oxygenField);
        vehiclePanel.add(lengthLabel); vehiclePanel.add(lengthField);
        vehiclePanel.add(foodLabel); vehiclePanel.add(foodField);
        vehiclePanel.add(crewLabel); vehiclePanel.add(crewField);

        JPanel crewPanel = new JPanel();
        crewPanel.setLayout(new GridLayout(0, 5));
        JScrollPane crewScrollPane = new JScrollPane(crewPanel);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double fuelCapacity = Double.parseDouble(fuelField.getText());
                double startingOxygen = Double.parseDouble(oxygenField.getText());
                double missionLength = Double.parseDouble(lengthField.getText());
                double food = Double.parseDouble(foodField.getText());
                int crewSize = Integer.parseInt(crewField.getText());
                
                MissionVehicle vehicle = new MissionVehicle(fuelCapacity, startingOxygen, missionLength, food, crewSize);
                crewPanel.removeAll();
                for (int i = 0; i < crewSize; i++) {
                    JPanel memberPanel = new JPanel(new GridLayout(5, 2));
                    JLabel nameLabel = new JLabel("Name:");
                    JTextField nameField = new JTextField();
                    JLabel weightLabel = new JLabel("Weight:");
                    JTextField weightField = new JTextField();
                    JLabel ageLabel = new JLabel("Age:");
                    JTextField ageField = new JTextField();
                    JLabel heightLabel = new JLabel("Height:");
                    JTextField heightField = new JTextField();
                    JLabel genderLabel = new JLabel("Gender:");
                    JTextField genderField = new JTextField();
                    memberPanel.add(nameLabel); memberPanel.add(nameField);
                    memberPanel.add(weightLabel); memberPanel.add(weightField);
                    memberPanel.add(ageLabel); memberPanel.add(ageField);
                    memberPanel.add(heightLabel); memberPanel.add(heightField);
                    memberPanel.add(genderLabel); memberPanel.add(genderField);
                    crewPanel.add(memberPanel);
                }
                frame.revalidate();
                frame.repaint();
            }
        });

        frame.add(vehiclePanel, BorderLayout.NORTH);
        frame.add(crewScrollPane, BorderLayout.CENTER);
        frame.add(submitButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}

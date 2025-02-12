import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class CrewMemberGUI {
    double weight;
    double age;
    double height;
    String gender;

    public CrewMemberGUI(double weight, double age, double height, String gender) {
        this.weight = weight;
        this.age = age;
        this.height = height;
        this.gender = gender;
    }

    public double OConsumption(int missionLength) { // TODO: add more inputs (CrewMember attributes)
        double InitialO = 10.0;
        for (int i = 1; i <= missionLength; i++) {
            InitialO -= 1.0; // TODO: change to real oxygen consumption calculation
        }
        return InitialO;
    }

    public double FoodConsumption(int missionLength) { // TODO: add more inputs (CrewMember attributes)
        double Consumed = 0.0;
        for (int i = 1; i <= missionLength; i++) {
            Consumed += 1.0; // TODO: change to real food consumption calculation
        }
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "weight=" + weight +
                ", age=" + age +
                ", height=" + height +
                ", gender='" + gender + '\'' +
                '}';
    }
}

class MissionVehicleGUI {
    double fuelCapacity;
    double startingOxygen;
    double missionLength;
    double food;
    int crewSize;
    ArrayList<CrewMemberGUI> CrewMemberGUIs;

    public MissionVehicleGUI(double fuelCapacity, double startingOxygen, double missionLength, double food, int crewSize) {
        this.fuelCapacity = fuelCapacity;
        this.startingOxygen = startingOxygen;
        this.missionLength = missionLength;
        this.food = food;
        this.crewSize = crewSize;
        this.CrewMemberGUIs = new ArrayList<>();
    }

    public void addCrewMemberGUI(CrewMemberGUI CrewMemberGUI) {
        if (CrewMemberGUIs.size() < crewSize) {
            CrewMemberGUIs.add(CrewMemberGUI);
        }
    }

    public double FuelUse(double fuelCapacity) {
        double CurrentFuel = fuelCapacity;
        for (int day = 1; day <= missionLength; day++) {
            CurrentFuel /= 1.5; // TODO: change to real fuel usage calculation
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
                ", crewMembers=" + CrewMemberGUIs +
                '}';
    }
}

public class MissionSetupGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mission Setup");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2));

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
                JOptionPane.showMessageDialog(frame, "Mission vehicle created: \n" + vehicle);
            }
        });

        frame.add(fuelLabel); frame.add(fuelField);
        frame.add(oxygenLabel); frame.add(oxygenField);
        frame.add(lengthLabel); frame.add(lengthField);
        frame.add(foodLabel); frame.add(foodField);
        frame.add(crewLabel); frame.add(crewField);
        frame.add(submitButton);

        frame.setVisible(true);
    }
}

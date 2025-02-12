import java.util.ArrayList;
import java.util.Scanner;

class CrewMember {
    double weight;
    double age;
    double height;
    String gender;

    public CrewMember(double weight, double age, double height, String gender) {
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
                "weight=" + weight +
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
        } else {
            System.out.println("Crew size limit reached.");
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

public class MissionSetup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Gather MissionVehicle details
        System.out.println("Enter Mission Vehicle details:");
        System.out.print("Fuel Capacity: ");
        double fuelCapacity = scanner.nextDouble();
        System.out.print("Starting Oxygen: ");
        double startingOxygen = scanner.nextDouble();
        System.out.print("Mission Length: ");
        double missionLength = scanner.nextDouble();
        System.out.print("Food Supply: ");
        double food = scanner.nextDouble();
        System.out.print("Crew Size: ");
        int crewSize = scanner.nextInt();

        MissionVehicle vehicle = new MissionVehicle(fuelCapacity, startingOxygen, missionLength, food, crewSize);

        // Gather CrewMember details
        for (int i = 0; i < crewSize; i++) {
            System.out.println("Enter details for Crew Member " + (i + 1) + ":");
            System.out.print("Weight: ");
            double weight = scanner.nextDouble();
            System.out.print("Age: ");
            double age = scanner.nextDouble();
            System.out.print("Height: ");
            double height = scanner.nextDouble();
            scanner.nextLine();  // Consume newline
            System.out.print("Gender: ");
            String gender = scanner.nextLine();

            CrewMember member = new CrewMember(weight, age, height, gender);
            vehicle.addCrewMember(member);
        }

        // Display the created MissionVehicle and its CrewMembers
        System.out.println("\nMission Vehicle Setup Complete:");
        System.out.println(vehicle);
    }
}

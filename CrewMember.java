public class CrewMember {
    private String name;
    private int age;
    private String sex;
    private double height;
    private double weight;
    private double exercise;

    // Constructor
    public CrewMember(String name, int age, String sex, double height, double weight, double exercise) {
        setName(name);
        setAge(age);
        setSex(sex);
        setHeight(height);
        setWeight(weight);
        setExercise(exercise);
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getExercise() {
        return exercise;
    }

    // Setter methods with validation
    public boolean setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
            return true;
        }
        return false;
    }

    public boolean setAge(int age) {
        if (age > 0 && age < 80) {
            this.age = age;
            return true;
        }
        System.out.println("Invalid age. Age must be between 1 and 79.");
        return false;
    }

    public boolean setSex(String sex) {
        if (
                sex != null
                        &&
                (
                sex.equalsIgnoreCase("Male")
                        ||
                sex.equalsIgnoreCase("M")
                        ||
                sex.equalsIgnoreCase("Female")
                        ||
                sex.equalsIgnoreCase("F")
                )
            )
        {
            this.sex = sex;
            return true;
        }
        System.out.println("Invalid sex. Must be either 'Male' or 'Female'.");
        return false;
    }

    public boolean setHeight(double height) {
        if (height > 100 && height < 250) {
            this.height = height;
            return true;
        }
        System.out.println("Invalid height. Height must be between 60 and 84.");
        return false;
    }

    public boolean setWeight(double weight) {
        if (weight > 50 && weight < 120) {
            this.weight = weight;
            return true;
        }
        System.out.println("Invalid weight. Weight must be between 50 and 120.");
        return false;
    }

    public boolean setExercise(double exercise) {
        if (exercise >= 0 && exercise < 12) {
            this.exercise = exercise;
            return true;
        }
        System.out.println("Invalid Exercise. Exercise must be between 0 and 12.");
        return false;
    }

    @Override
    public String toString() {
        return "Name : " + getName() + "\n" +
                "Age : " + getAge() + "\n" +
                "Sex : " + getSex() + "\n" +
                "Height : " + getHeight() + "\n" +
                "Weight : " + getWeight() + "\n" +
                "Exercise : " + getExercise() + "\n";
    }
}
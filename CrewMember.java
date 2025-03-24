public class CrewMember {
    private String name;
    private int age;
    private String sex;
    private double height;
    private double weight;

    // Constructor
    public CrewMember(String name, int age, String sex, double height, double weight) {
        this.name = name;
        this.setAge(age);
        this.setSex(sex);
        this.setHeight(height);
        this.setWeight(weight);
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
        if (sex != null && (sex.equalsIgnoreCase("Male") || sex.equalsIgnoreCase("Female"))) {
            this.sex = sex;
            return true;
        }
        System.out.println("Invalid sex. Must be either 'Male' or 'Female'.");
        return false;
    }

    public boolean setHeight(double height) {
        if (height > 60 && height < 84) {
            this.height = height;
            return true;
        }
        System.out.println("Invalid height. Height must be between 60 and 84.");
        return false;
    }

    public boolean setWeight(double weight) {
        if (weight > 100 && weight < 250) {
            this.weight = weight;
            return true;
        }
        System.out.println("Invalid weight. Weight must be between 100 and 250.");
        return false;
    }
}
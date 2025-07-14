public class Student {
    private String fullName;    // Example: "Rahimov_Samir"
    private String studentId;   // Example: "ADA12345"
    private String city;        // Example: "Baku"
    private int year;           // Example: 3
    private double gpa;         // Example: 3.75
    private String major;       // Example: "Computer Science"

    public Student(String fullName, String studentId, String city, int year, double gpa, String major) {
        this.fullName = fullName;
        this.studentId = studentId;
        this.city = city;
        this.year = year;
        this.gpa = gpa;
        this.major = major;
    }

    // Getters and setters (only one example below; do others similarly)
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    @Override
    public String toString() {
        return "Name: " + fullName +
               ", ID: " + studentId +
               ", City: " + city +
               ", Year: " + year +
               ", GPA: " + gpa +
               ", Major: " + major;
    }

    // Optionally, add a method for file saving (we will use later)
    public String toFileString() {
        return fullName + "," + studentId + "," + city + "," + year + "," + gpa + "," + major;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        return new Student(parts[0], parts[1], parts[2],
                           Integer.parseInt(parts[3]),
                           Double.parseDouble(parts[4]),
                           parts[5]);
    }
}

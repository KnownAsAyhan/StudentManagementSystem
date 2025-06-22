package StudentManagement;

public class Student {
    private String fullName;
    private String studentId;
    private String department;
    private int year;
    private double gpa;
    private String city;

    public Student(String fullName, String studentId, String department, int year, double gpa, String city) {
        this.fullName = fullName;
        this.studentId = studentId;
        this.department = department;
        this.year = year;
        this.gpa = gpa;
        this.city = city;
    }

    public String getFullName() { return fullName; }
    public String getStudentId() { return studentId; }
    public String getDepartment() { return department; }
    public int getYear() { return year; }
    public double getGpa() { return gpa; }
    public String getCity() { return city; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setDepartment(String department) { this.department = department; }
    public void setYear(int year) { this.year = year; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    public void setCity(String city) { this.city = city; }

    @Override
    public String toString() {
        return fullName + ";" + studentId + ";" + department + ";" + year + ";" + gpa + ";" + city;
    }


}

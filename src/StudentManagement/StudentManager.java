package StudentManagement;

import java.util.*;

public class StudentManager {
    private final ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
        System.out.println("Student added successfully.");
    }

    public void listStudents() {
        System.out.println("Total students: " + students.size());
        for (Student s : students) {
            displayStudent(s);
        }
    }



    public void deleteStudent(Student s) {
        students.remove(s);
        System.out.println("Student deleted.");
    }

    public void editStudent(Student s, Scanner input) {
        System.out.print("Enter new full name: ");
        s.setFullName(input.nextLine());
        System.out.print("Enter new department: ");
        s.setDepartment(input.nextLine());
        System.out.print("Enter new year: ");
        s.setYear(Integer.parseInt(input.nextLine()));
        System.out.print("Enter new GPA: ");
        s.setGpa(Double.parseDouble(input.nextLine()));
        System.out.print("Enter new city: ");
        s.setCity(input.nextLine());
        System.out.println("Student updated.");
    }



    public ArrayList<Student> getStudents() {
        return students;
    }



    public static void displayStudent(Student s) {
        System.out.println("------------------------------");
        System.out.println("Name       : " + s.getFullName());
        System.out.println("ID         : " + s.getStudentId());
        System.out.println("Department : " + s.getDepartment());
        System.out.println("Year       : " + s.getYear());
        System.out.println("GPA        : " + s.getGpa());
        System.out.println("City       : " + s.getCity());
        System.out.println("------------------------------");
    }

}


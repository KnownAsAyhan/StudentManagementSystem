package StudentManagement;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    // Load students from file
    public static ArrayList<Student> loadStudents(String filename) {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    String fullName = parts[0];
                    String studentId = parts[1];
                    String department = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    double gpa = Double.parseDouble(parts[4]);
                    String city = parts[5];
                    students.add(new Student(fullName, studentId, department, year, gpa, city));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
        return students;
    }

    // Save students to file
    public static void saveStudents(String filename, ArrayList<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student s : students) {
                writer.write(s.toString());
                writer.newLine();
            }
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }
}

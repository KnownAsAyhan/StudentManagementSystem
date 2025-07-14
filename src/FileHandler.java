import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    public static void saveStudentsToFile(ArrayList<Student> list) {
        try (PrintWriter writer = new PrintWriter("data/students.txt")) {
            for (Student s : list) {
                writer.println(s.toFileString());
            }
            System.out.println("Data saved.");
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public static void loadStudentsFromFile(ArrayList<Student> list) {
        list.clear();
        File file = new File("data/students.txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Student.fromFileString(line));
            }
            System.out.println("Data loaded.");
        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }
}

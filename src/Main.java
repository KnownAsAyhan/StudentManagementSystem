import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class
Main {
    private static final ArrayList<Student> studentList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    loadStudentsFromFile(); // Load on start
    boolean running = true;

    while (running) {
        System.out.println("\n--- Student Management System ---");
        System.out.println("1. Add Student");
        System.out.println("2. List Students");
        System.out.println("3. Search Student");
        System.out.println("4. Sort Students");
        System.out.println("5. Advanced Filter Search");
        System.out.println("6. Exit");

        String choice = scanner.nextLine();
        switch (choice) {
        case "1" -> addStudent();
        case "2" -> listStudents();
        case "3" -> searchStudent();
        case "4" -> sortStudents();
        case "5" -> filterStudents();
        case "6" -> {
        saveStudentsToFile();
        running = false;
            }
        default -> System.out.println("Invalid option. Try again.");
}



    }
}


    private static void addStudent() {
        System.out.print("Full Name (e.g. Rahimov_Samir): ");
        String name = scanner.nextLine();

        System.out.print("Student ID: ");
        String id = scanner.nextLine();

        System.out.print("City: ");
        String city = scanner.nextLine();

        System.out.print("Year (1–4): ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.print("GPA (0.0–4.0): ");
        double gpa = Double.parseDouble(scanner.nextLine());

        System.out.print("Major: ");
        String major = scanner.nextLine();

        Student s = new Student(name, id, city, year, gpa, major);
        studentList.add(s);
        System.out.println("Student added successfully!");
    }

    private static void listStudents() {
        System.out.println("\n--- List of Students ---");
        System.out.println("Total: " + studentList.size());
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    private static void searchStudent() {
        System.out.print("Enter any field to search (name, ID, city, major, year, GPA): ");
        String keyword = scanner.nextLine().toLowerCase();

        ArrayList<Student> matches = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getFullName().toLowerCase().contains(keyword) ||
                s.getStudentId().toLowerCase().contains(keyword) ||
                s.getCity().toLowerCase().contains(keyword) ||
                s.getMajor().toLowerCase().contains(keyword) ||
                String.valueOf(s.getYear()).equals(keyword) ||
                String.valueOf(s.getGpa()).equals(keyword)) {
                matches.add(s);
            }
        }

        if (matches.isEmpty()) {
            System.out.println("No matching student found.");
        } else {
            System.out.println("\n--- Search Results ---");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println((i + 1) + ". " + matches.get(i));
            }

            System.out.print("Select student number to edit/delete (or 0 to cancel): ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice > 0 && choice <= matches.size()) {
                Student selected = matches.get(choice - 1);
                System.out.print("Edit (E) / Delete (D) / Cancel (C): ");
                String action = scanner.nextLine().toUpperCase();

                switch (action) {
                    case "E" -> editStudent(selected);
                    case "D" -> {
                        studentList.remove(selected);
                        System.out.println("Student deleted.");
                    }
                    case "C" -> System.out.println("Canceled.");
                    default -> System.out.println("Invalid option.");
                }
            }
        }
    }

    private static void editStudent(Student s) {
        System.out.println("Editing student: " + s);

        System.out.print("New Full Name (leave empty to keep): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) s.setFullName(name);

        System.out.print("New ID: ");
        String id = scanner.nextLine();
        if (!id.isEmpty()) s.setStudentId(id);

        System.out.print("New City: ");
        String city = scanner.nextLine();
        if (!city.isEmpty()) s.setCity(city);

        System.out.print("New Year: ");
        String yearStr = scanner.nextLine();
        if (!yearStr.isEmpty()) s.setYear(Integer.parseInt(yearStr));

        System.out.print("New GPA: ");
        String gpaStr = scanner.nextLine();
        if (!gpaStr.isEmpty()) s.setGpa(Double.parseDouble(gpaStr));

        System.out.print("New Major: ");
        String major = scanner.nextLine();
        if (!major.isEmpty()) s.setMajor(major);

        System.out.println("Student updated!");
    }

    private static void saveStudentsToFile() {
    try (PrintWriter writer = new PrintWriter("data/students.txt")) {
        for (Student s : studentList) {
            writer.println(s.toFileString());
        }
        System.out.println("Data saved to file.");
    } catch (IOException e) {
        System.out.println("Error saving to file: " + e.getMessage());
    }
}

private static void loadStudentsFromFile() {
    File file = new File("data/students.txt");
    if (!file.exists()) {
        System.out.println("No existing data found. Starting fresh.");
        return;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            Student s = Student.fromFileString(line);
            studentList.add(s);
        }
        System.out.println("Loaded " + studentList.size() + " students from file.");
    } catch (IOException e) {
        System.out.println("Error reading from file: " + e.getMessage());
    }
} 


private static void sortStudents() {
    System.out.println("\nSort by:");
    System.out.println("1. Name");
    System.out.println("2. Student ID");
    System.out.println("3. City");
    System.out.println("4. Year");
    System.out.println("5. GPA");
    System.out.println("6. Major");
    System.out.print("Choose an attribute to sort by: ");
    String choice = scanner.nextLine();

    switch (choice) {
        case "1" -> studentList.sort((s1, s2) -> s1.getFullName().compareToIgnoreCase(s2.getFullName()));
        case "2" -> studentList.sort((s1, s2) -> s1.getStudentId().compareToIgnoreCase(s2.getStudentId()));
        case "3" -> studentList.sort((s1, s2) -> s1.getCity().compareToIgnoreCase(s2.getCity()));
        case "4" -> studentList.sort((s1, s2) -> Integer.compare(s1.getYear(), s2.getYear()));
        case "5" -> studentList.sort((s1, s2) -> Double.compare(s1.getGpa(), s2.getGpa()));
        case "6" -> studentList.sort((s1, s2) -> s1.getMajor().compareToIgnoreCase(s2.getMajor()));
        default -> {
            System.out.println("Invalid choice.");
            return;
            }
    }

    System.out.println("Students sorted successfully!");
    listStudents(); // Show the sorted list
}

private static void filterStudents() {
    System.out.println("\nEnter filter values (leave blank to skip that attribute):");

    System.out.print("Filter by Name: ");
    String nameFilter = scanner.nextLine().toLowerCase();

    System.out.print("Filter by City: ");
    String cityFilter = scanner.nextLine().toLowerCase();

    System.out.print("Filter by Major: ");
    String majorFilter = scanner.nextLine().toLowerCase();

    System.out.print("Filter by Year (e.g., 3): ");
    String yearFilter = scanner.nextLine();

    System.out.print("Filter by Min GPA (e.g., 3.0): ");
    String minGpaFilter = scanner.nextLine();

    System.out.print("Filter by Max GPA (e.g., 4.0): ");
    String maxGpaFilter = scanner.nextLine();

    // Apply filters using Stream
    studentList.stream()
        .filter(s -> nameFilter.isEmpty() || s.getFullName().toLowerCase().contains(nameFilter))
        .filter(s -> cityFilter.isEmpty() || s.getCity().toLowerCase().contains(cityFilter))
        .filter(s -> majorFilter.isEmpty() || s.getMajor().toLowerCase().contains(majorFilter))
        .filter(s -> yearFilter.isEmpty() || s.getYear() == Integer.parseInt(yearFilter))
        .filter(s -> minGpaFilter.isEmpty() || s.getGpa() >= Double.parseDouble(minGpaFilter))
        .filter(s -> maxGpaFilter.isEmpty() || s.getGpa() <= Double.parseDouble(maxGpaFilter))
        .forEach(System.out::println);
}



}

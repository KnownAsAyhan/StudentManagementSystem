package StudentManagement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String filePath = "data/students.txt";
        StudentManager manager = new StudentManager();
        manager.getStudents().addAll(FileHandler.loadStudents(filePath));

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add new student");
            System.out.println("2. List all students");
            System.out.println("3. Search student");
            System.out.println("4. Sort students");
            System.out.println("5. Save and Exit");
            System.out.print("Choose an option: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    String name;
                    do {
                        System.out.print("Full name: ");
                        name = input.nextLine();
                    } while (!name.matches("[A-Za-z ]+"));

                    String id;
                    do {
                        System.out.print("Student ID (5 digits): ");
                        id = input.nextLine();
                    } while (!id.matches("\\d{5}"));

                    String dept;
                    do {
                        System.out.print("Department: ");
                        dept = input.nextLine();
                    } while (!dept.matches("[A-Za-z ]+"));

                    int year;
                    while (true) {
                        System.out.print("Year (e.g., 2027): ");
                        String yearStr = input.nextLine();
                        if (yearStr.matches("\\d{4}")) {
                            year = Integer.parseInt(yearStr);
                            break;
                        }
                    }

                    double gpa;
                    while (true) {
                        System.out.print("GPA (0.0 to 4.0): ");
                        try {
                            gpa = Double.parseDouble(input.nextLine());
                            if (gpa >= 0.0 && gpa <= 4.0) break;
                        } catch (Exception e) {
                            // invalid input, try again
                        }
                    }

                    String city;
                    do {
                        System.out.print("City: ");
                        city = input.nextLine();
                    } while (!city.matches("[A-Za-z ]+"));

                    Student s = new Student(name, id, dept, year, gpa, city);
                    manager.addStudent(s);
                    break;

                case "2":
                    manager.listStudents();
                    break;

                case "3":
                    System.out.println("Search by:");
                    System.out.println("1. ID");
                    System.out.println("2. Full Name");
                    System.out.println("3. Department");
                    System.out.println("4. Year");
                    System.out.println("5. GPA");
                    System.out.println("6. City");
                    System.out.print("Choose an option: ");
                    String searchChoice = input.nextLine();

                    System.out.print("Enter search value: ");
                    String searchValue = input.nextLine();

                    ArrayList<Student> matches = new ArrayList<>();

                    for (Student st : manager.getStudents()) {
                        switch (searchChoice) {
                            case "1":
                                if (st.getStudentId().equalsIgnoreCase(searchValue)) matches.add(st);
                                break;
                            case "2":
                                if (st.getFullName().equalsIgnoreCase(searchValue)) matches.add(st);
                                break;
                            case "3":
                                if (st.getDepartment().equalsIgnoreCase(searchValue)) matches.add(st);
                                break;
                            case "4":
                                if (Integer.toString(st.getYear()).equals(searchValue)) matches.add(st);
                                break;
                            case "5":
                                if (Double.toString(st.getGpa()).equals(searchValue)) matches.add(st);
                                break;
                            case "6":
                                if (st.getCity().equalsIgnoreCase(searchValue)) matches.add(st);
                                break;
                        }
                    }

                    if (!matches.isEmpty()) {
                        System.out.println("Matching students:");
                        for (Student st : matches) {
                            System.out.println("------------------------------");
                            System.out.println("Name       : " + st.getFullName());
                            System.out.println("ID         : " + st.getStudentId());
                            System.out.println("Department : " + st.getDepartment());
                            System.out.println("Year       : " + st.getYear());
                            System.out.println("GPA        : " + st.getGpa());
                            System.out.println("City       : " + st.getCity());
                            System.out.println("------------------------------");
                        }

                        System.out.print("Do you want to (e)dit, (d)elete, or (s)kip?: ");
                        String action = input.nextLine();

                        if (action.equalsIgnoreCase("e") || action.equalsIgnoreCase("d")) {
                            System.out.print("Enter the ID of the student you want to " + (action.equalsIgnoreCase("e") ? "edit" : "delete") + ": ");
                            String idChoice = input.nextLine();

                            Student selected = null;
                            for (Student match : matches) {
                                if (match.getStudentId().equalsIgnoreCase(idChoice)) {
                                    selected = match;
                                    break;
                                }
                            }

                            if (selected != null) {
                                if (action.equalsIgnoreCase("e")) {
                                    manager.editStudent(selected, input);
                                } else {
                                    manager.deleteStudent(selected);
                                }
                            } else {
                                System.out.println("No matching student with that ID.");
                            }
                        } else {
                            System.out.println("Returning to main menu...");
                        }


                    } else {
                        System.out.println("No students found matching the criteria.");
                    }
                    break;




                case "4":
                    System.out.println("Sort by:");
                    System.out.println("1. Full Name");
                    System.out.println("2. Student ID");
                    System.out.println("3. Department");
                    System.out.println("4. Year");
                    System.out.println("5. GPA");
                    System.out.println("6. City");
                    System.out.print("Choose an option: ");
                    String sortChoice = input.nextLine();

                    switch (sortChoice) {
                        case "1":
                            manager.getStudents().sort(Comparator.comparing(Student::getFullName));
                            System.out.println("Sorted by Full Name.");
                            break;
                        case "2":
                            manager.getStudents().sort(Comparator.comparing(Student::getStudentId));
                            System.out.println("Sorted by Student ID.");
                            break;
                        case "3":
                            manager.getStudents().sort(Comparator.comparing(Student::getDepartment));
                            System.out.println("Sorted by Department.");
                            break;
                        case "4":
                            manager.getStudents().sort(Comparator.comparingInt(Student::getYear));
                            System.out.println("Sorted by Year.");
                            break;
                        case "5":
                            manager.getStudents().sort(Comparator.comparingDouble(Student::getGpa).reversed());
                            System.out.println("Sorted by GPA (high to low).");
                            break;
                        case "6":
                            manager.getStudents().sort(Comparator.comparing(Student::getCity));
                            System.out.println("Sorted by City.");
                            break;
                        default:
                            System.out.println("Invalid sort option.");
                            break;
                    }

                    // Immediately show the sorted list
                    if (!manager.getStudents().isEmpty()) {
                        System.out.println("Sorted List:");
                        for (Student st : manager.getStudents()) {
                            System.out.println("------------------------------");
                            System.out.println("Name       : " + st.getFullName());
                            System.out.println("ID         : " + st.getStudentId());
                            System.out.println("Department : " + st.getDepartment());
                            System.out.println("Year       : " + st.getYear());
                            System.out.println("GPA        : " + st.getGpa());
                            System.out.println("City       : " + st.getCity());
                            System.out.println("------------------------------");
                        }
                    } else {
                        System.out.println("No students to show.");
                    }
                    break;



                case "5":
                    FileHandler.saveStudents(filePath, manager.getStudents());
                    System.out.println("Exiting... Bye!");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

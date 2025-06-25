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


    public ArrayList<Student> search(String field, String value) {
        ArrayList<Student> results = new ArrayList<>();
        for (Student st : students) {
            switch (field.toLowerCase()) {
                case "id":
                    if (st.getStudentId().equalsIgnoreCase(value)) results.add(st);
                    break;
                case "name":
                    if (st.getFullName().equalsIgnoreCase(value)) results.add(st);
                    break;
                case "department":
                    if (st.getDepartment().equalsIgnoreCase(value)) results.add(st);
                    break;
                case "year":
                    if (Integer.toString(st.getYear()).equals(value)) results.add(st);
                    break;
                case "gpa":
                    if (Double.toString(st.getGpa()).equals(value)) results.add(st);
                    break;
                case "city":
                    if (st.getCity().equalsIgnoreCase(value)) results.add(st);
                    break;
            }
        }
        return results;
    }



    public void sortBy(String field) {
        switch (field.toLowerCase()) {
            case "name":
                students.sort(Comparator.comparing(Student::getFullName));
                break;
            case "id":
                students.sort(Comparator.comparing(Student::getStudentId));
                break;
            case "department":
                students.sort(Comparator.comparing(Student::getDepartment));
                break;
            case "year":
                students.sort(Comparator.comparingInt(Student::getYear));
                break;
            case "gpa":
                students.sort(Comparator.comparingDouble(Student::getGpa).reversed());
                break;
            case "city":
                students.sort(Comparator.comparing(Student::getCity));
                break;
            default:
                System.out.println("Invalid sort field.");
        }
    }




    public ArrayList<Student> filter(String name, String dept, String city,
                                     String minYearStr, String maxYearStr,
                                     String minGpaStr, String maxGpaStr) {

        ArrayList<Student> filtered = new ArrayList<>();

        for (Student st : students) {
            boolean match = true;

            if (!name.isEmpty() && !st.getFullName().equalsIgnoreCase(name)) match = false;
            if (!dept.isEmpty() && !st.getDepartment().equalsIgnoreCase(dept)) match = false;
            if (!city.isEmpty() && !st.getCity().equalsIgnoreCase(city)) match = false;

            try {
                if (!minYearStr.isEmpty()) {
                    int minYear = Integer.parseInt(minYearStr);
                    if (st.getYear() < minYear) match = false;
                }
                if (!maxYearStr.isEmpty()) {
                    int maxYear = Integer.parseInt(maxYearStr);
                    if (st.getYear() > maxYear) match = false;
                }
                if (!minGpaStr.isEmpty()) {
                    double minGpa = Double.parseDouble(minGpaStr);
                    if (st.getGpa() < minGpa) match = false;
                }
                if (!maxGpaStr.isEmpty()) {
                    double maxGpa = Double.parseDouble(maxGpaStr);
                    if (st.getGpa() > maxGpa) match = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid filter value. Skipping entry.");
                match = false;
            }

            if (match) filtered.add(st);
        }

        return filtered;
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


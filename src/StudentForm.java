import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class StudentForm {

    public static void display(ArrayList<Student> studentList, TableView<Student> table, Student existing) {
        Stage window = new Stage();
        window.setTitle(existing == null ? "Add Student" : "Edit Student");

        TextField nameField = new TextField();
        TextField idField = new TextField();
        TextField cityField = new TextField();
        TextField yearField = new TextField();
        TextField gpaField = new TextField();
        TextField majorField = new TextField();

        if (existing != null) {
            nameField.setText(existing.getFullName());
            idField.setText(existing.getStudentId());
            cityField.setText(existing.getCity());
            yearField.setText(String.valueOf(existing.getYear()));
            gpaField.setText(String.valueOf(existing.getGpa()));
            majorField.setText(existing.getMajor());
        }

        Button submitBtn = new Button(existing == null ? "Add" : "Update");
        submitBtn.setOnAction(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            String city = cityField.getText();
            int year = Integer.parseInt(yearField.getText());
            double gpa = Double.parseDouble(gpaField.getText());
            String major = majorField.getText();

            if (existing == null) {
                Student s = new Student(name, id, city, year, gpa, major);
                studentList.add(s);
            } else {
                existing.setFullName(name);
                existing.setStudentId(id);
                existing.setCity(city);
                existing.setYear(year);
                existing.setGpa(gpa);
                existing.setMajor(major);
            }

            table.getItems().setAll(studentList);
            window.close();
        });

        VBox layout = new VBox(10,
                new Label("Full Name"), nameField,
                new Label("Student ID"), idField,
                new Label("City"), cityField,
                new Label("Year"), yearField,
                new Label("GPA"), gpaField,
                new Label("Major"), majorField,
                submitBtn
        );
        layout.setPadding(new Insets(15));

        window.setScene(new Scene(layout, 300, 400));
        window.showAndWait();
    }
}


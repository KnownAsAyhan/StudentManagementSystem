import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterForm {

    public static void display(ArrayList<Student> originalList, TableView<Student> table) {
        Stage window = new Stage();
        window.setTitle("Filter Students");

        TextField nameField = new TextField();
        TextField cityField = new TextField();
        TextField yearField = new TextField();
        TextField minGpaField = new TextField();
        TextField maxGpaField = new TextField();
        TextField majorField = new TextField();

        Button applyBtn = new Button("Apply Filter");
        applyBtn.setOnAction(e -> {
            String name = nameField.getText().toLowerCase();
            String city = cityField.getText().toLowerCase();
            String yearText = yearField.getText();
            String minGpaText = minGpaField.getText();
            String maxGpaText = maxGpaField.getText();
            String major = majorField.getText().toLowerCase();

            var filtered = originalList.stream()
                .filter(s -> name.isEmpty() || s.getFullName().toLowerCase().contains(name))
                .filter(s -> city.isEmpty() || s.getCity().toLowerCase().contains(city))
                .filter(s -> major.isEmpty() || s.getMajor().toLowerCase().contains(major))
                .filter(s -> yearText.isEmpty() || s.getYear() == Integer.parseInt(yearText))
                .filter(s -> minGpaText.isEmpty() || s.getGpa() >= Double.parseDouble(minGpaText))
                .filter(s -> maxGpaText.isEmpty() || s.getGpa() <= Double.parseDouble(maxGpaText))
                .collect(Collectors.toList());

            table.getItems().setAll(filtered);
            window.close();
        });

        VBox layout = new VBox(10,
                new Label("Name:"), nameField,
                new Label("City:"), cityField,
                new Label("Major:"), majorField,
                new Label("Year (1-4):"), yearField,
                new Label("Min GPA:"), minGpaField,
                new Label("Max GPA:"), maxGpaField,
                applyBtn
        );
        layout.setPadding(new Insets(15));

        window.setScene(new Scene(layout, 300, 400));
        window.showAndWait();
    }
}


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.util.ArrayList;

public class MainApp extends Application {

    private TableView<Student> tableView;
    private static final ArrayList<Student> studentList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Management System");

        // Table setup
        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setupTableColumns();

        // Buttons
        Button addBtn = new Button("Add");
        Button editBtn = new Button("Edit");
        Button deleteBtn = new Button("Delete");
        Button loadBtn = new Button("Load");
        Button saveBtn = new Button("Save");
        Button filterBtn = new Button("Filter");

        // Button actions
        addBtn.setOnAction(e -> StudentForm.display(studentList, tableView, null));
        editBtn.setOnAction(e -> {
            Student selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                StudentForm.display(studentList, tableView, selected);
            }
        });
        deleteBtn.setOnAction(e -> {
            Student selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                studentList.remove(selected);
                refreshTable();
            }
        });
        loadBtn.setOnAction(e -> {
            FileHandler.loadStudentsFromFile(studentList);
            refreshTable();
        });
        saveBtn.setOnAction(e -> FileHandler.saveStudentsToFile(studentList)); 
        filterBtn.setOnAction(e -> FilterForm.display(studentList, tableView));
        


        // Layout
        HBox buttons = new HBox(10, addBtn, editBtn, deleteBtn, filterBtn, loadBtn, saveBtn);
        buttons.setPadding(new Insets(10));

        VBox root = new VBox(10, tableView, buttons);
        root.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();
    }

    private void setupTableColumns() {
        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        TableColumn<Student, String> idCol = new TableColumn<>("Student ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("studentId"));

        TableColumn<Student, String> cityCol = new TableColumn<>("City");
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Student, Integer> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Student, Double> gpaCol = new TableColumn<>("GPA");
        gpaCol.setCellValueFactory(new PropertyValueFactory<>("gpa"));

        TableColumn<Student, String> majorCol = new TableColumn<>("Major");
        majorCol.setCellValueFactory(new PropertyValueFactory<>("major"));

        tableView.getColumns().addAll(nameCol, idCol, cityCol, yearCol, gpaCol, majorCol);
    }

    private void refreshTable() {
        tableView.getItems().setAll(studentList);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

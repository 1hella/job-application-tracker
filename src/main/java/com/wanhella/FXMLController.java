package com.wanhella;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TableView<JobApplication> tableView;

    @FXML
    private TableColumn<JobApplication, String> companyNameColumn;
    @FXML
    private TableColumn<JobApplication, String> positionTitleColumn;
    @FXML
    private TableColumn<JobApplication, String> websiteLinkColumn;
    @FXML
    private TableColumn<JobApplication, String> addressColumn;
    @FXML
    private TableColumn<JobApplication, String> contactNameColumn;
    @FXML
    private TableColumn<JobApplication, String> phoneNumberColumn;
    @FXML
    private TableColumn<JobApplication, Double> jobPayColumn;
    @FXML
    private TableColumn<JobApplication, LocalDate> dateAppliedColumn;
    @FXML
    private TableColumn<JobApplication, LocalDate> interview1DateColumn;
    @FXML
    private TableColumn<JobApplication, LocalDate> interview2DateColumn;
    @FXML
    private TableColumn<JobApplication, LocalDate> interview3DateColumn;
    @FXML
    private TableColumn<JobApplication, String> notesColumn;
    @FXML
    private TableColumn<JobApplication, Integer> daysSinceApplyingColumn;
    @FXML
    private TableColumn<JobApplication, Integer> daysSinceInterview1Column;
    @FXML
    private TableColumn<JobApplication, Integer> daysSinceInterview2Column;
    @FXML
    private TableColumn<JobApplication, Integer> daysSinceInterview3Column;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");

        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        positionTitleColumn.setCellValueFactory(new PropertyValueFactory<>("positionTitle"));
        websiteLinkColumn.setCellValueFactory(new PropertyValueFactory<>("websiteLink"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        contactNameColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        jobPayColumn.setCellValueFactory(new PropertyValueFactory<>("jobPay"));
        dateAppliedColumn.setCellValueFactory(new PropertyValueFactory<>("dateApplied"));
        interview1DateColumn.setCellValueFactory(new PropertyValueFactory<>("interview1Date"));
        interview2DateColumn.setCellValueFactory(new PropertyValueFactory<>("interview2Date"));
        interview3DateColumn.setCellValueFactory(new PropertyValueFactory<>("interview3Date"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        daysSinceApplyingColumn.setCellValueFactory(new PropertyValueFactory<>("daysSinceApplying"));
        daysSinceInterview1Column.setCellValueFactory(new PropertyValueFactory<>("daysSinceInterview1"));
        daysSinceInterview2Column.setCellValueFactory(new PropertyValueFactory<>("daysSinceInterview2"));
        daysSinceInterview3Column.setCellValueFactory(new PropertyValueFactory<>("daysSinceInterview3"));

        JobApplicationDBLoader jobApplicationDBLoader = new JobApplicationDBLoader();
        List<JobApplication> jobApplications = jobApplicationDBLoader.loadJobApplications();
        tableView.setItems(FXCollections.observableArrayList(jobApplications));
    }
}
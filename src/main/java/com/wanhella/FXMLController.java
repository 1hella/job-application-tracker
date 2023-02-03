package com.wanhella;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

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

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    private JobApplication selectedApplication;
    private ObservableList<JobApplication> jobApplications;
    private JobApplicationDBLoader jobApplicationDBLoader;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        jobApplicationDBLoader = new JobApplicationDBLoader();
        jobApplications = FXCollections.observableArrayList(jobApplicationDBLoader.loadJobApplications());
        tableView.setItems(jobApplications);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedApplication = newSelection;
            }
        });

        deleteButton.setOnAction(event -> {
            if (selectedApplication != null) {
                jobApplicationDBLoader.deleteJobApplication(selectedApplication);
                jobApplications.setAll(jobApplicationDBLoader.loadJobApplications());
            }
        });

        addButton.setOnAction(event -> {
            System.out.println("Add button clicked!");
        });
    }
}
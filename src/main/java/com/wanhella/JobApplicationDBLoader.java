package com.wanhella;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDBLoader {
    private static final String URL = "jdbc:postgresql://localhost:5432";

    private static final String DB_NAME = "job_applications";
    private static final String USER = "postgres";
    private static final String PASSWORD = System.getProperty("DB_PASSWORD");

    public JobApplicationDBLoader() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("""
                    CREATE TABLE IF NOT EXISTS %s (
                        id SERIAL PRIMARY KEY,
                        company_name TEXT NOT NULL,
                        position_title TEXT NOT NULL,
                        website_link TEXT NOT NULL,
                        address TEXT NOT NULL,
                        contact_name TEXT NOT NULL,
                        phone_number TEXT NOT NULL,
                        job_pay NUMERIC(10, 2) NOT NULL,
                        date_applied DATE NOT NULL,
                        interview1_date DATE,
                        interview2_date DATE,
                        interview3_date DATE,
                        notes TEXT,
                    );""".formatted(DB_NAME));
            statement.executeQuery();
            System.out.println("Created Table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<JobApplication> loadJobApplications() {
        List<JobApplication> jobApplications = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM job_applications");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String companyName = resultSet.getString("company_name");
                String positionTitle = resultSet.getString("position_title");
                String websiteLink = resultSet.getString("website_link");
                String address = resultSet.getString("address");
                String contactName = resultSet.getString("contact_name");
                String phoneNumber = resultSet.getString("phone_number");
                double jobPay = resultSet.getDouble("job_pay");
                LocalDate dateApplied = resultSet.getDate("date_applied").toLocalDate();
                LocalDate interview1Date = resultSet.getDate("interview1_date").toLocalDate();
                LocalDate interview2Date = resultSet.getDate("interview2_date").toLocalDate();
                LocalDate interview3Date = resultSet.getDate("interview3_date").toLocalDate();
                String notes = resultSet.getString("notes");

                JobApplication jobApplication = new JobApplication(companyName, positionTitle, websiteLink, address, contactName, phoneNumber, jobPay, dateApplied, interview1Date, interview2Date, interview3Date, notes);
                jobApplications.add(jobApplication);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobApplications;
    }

    public void updateJobApplication(JobApplication jobApplication) {

    }

    //create
    //delete
}

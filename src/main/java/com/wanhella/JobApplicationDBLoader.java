package com.wanhella;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDBLoader {
    private static final String URL = "jdbc:postgresql://localhost:5432/job_applications";

    private static final String TABLE_NAME = "job_applications";
    private static final String USER = "postgres";
    private static final String PASSWORD = System.getProperty("DB_PASSWORD");

    public JobApplicationDBLoader() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("""
                    CREATE TABLE IF NOT EXISTS %s (
                        id SERIAL PRIMARY KEY,
                        company_name TEXT NOT NULL,
                        position_title TEXT NOT NULL,
                        website_link TEXT,
                        address TEXT,
                        contact_name TEXT,
                        phone_number TEXT,
                        job_pay NUMERIC(10, 2),
                        date_applied DATE NOT NULL,
                        interview1_date DATE,
                        interview2_date DATE,
                        interview3_date DATE,
                        notes TEXT
                    );""".formatted(TABLE_NAME));
            statement.execute();

            PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            ResultSet resultSet = statement1.executeQuery();

            if (!resultSet.next()) { // no sample data or any rows
                PreparedStatement statement2 = connection.prepareStatement("""
                        INSERT INTO job_applications (company_name, position_title, website_link, address, contact_name, phone_number, job_pay, date_applied, interview1_date, interview2_date, interview3_date, notes)
                        VALUES
                        ('Google', 'Software Engineer', 'www.google.com', '1600 Amphitheatre Parkway, Mountain View, CA 94043, USA', 'John Doe', '123-456-7890', 100000.00, '2022-01-01', '2022-02-01', '2022-03-01', '2022-04-01', 'Good company'),
                        ('Amazon', 'DevOps Engineer', 'www.amazon.com', '410 Terry Ave N, Seattle, WA 98109, USA', 'Jane Doe', '987-654-3210', 120000.00, '2022-05-01', '2022-06-01', '2022-07-01', NULL, 'Good opportunity'),
                        ('Microsoft', 'Data Scientist', 'www.microsoft.com', 'One Microsoft Way, Redmond, WA 98052, USA', 'Jim Smith', '456-123-7890', 110000.00, '2022-08-01', '2022-09-01', NULL, NULL, 'Interesting projects');
                        """);
                statement2.execute();
            }
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

                Date interview1DateTemp = resultSet.getDate("interview1_date");
                LocalDate interview1Date = interview1DateTemp != null ? interview1DateTemp.toLocalDate() : null;

                Date interview2DateTemp = resultSet.getDate("interview2_date");
                LocalDate interview2Date = interview2DateTemp != null ? interview2DateTemp.toLocalDate() : null;

                Date interview3DateTemp = resultSet.getDate("interview3_date");
                LocalDate interview3Date = interview3DateTemp != null ? interview3DateTemp.toLocalDate() : null;

                String notes = resultSet.getString("notes");
                long id = resultSet.getLong("id");

                JobApplication jobApplication = new JobApplication(companyName, positionTitle, websiteLink, address, contactName, phoneNumber, jobPay, dateApplied, interview1Date, interview2Date, interview3Date, notes, id);
                jobApplications.add(jobApplication);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobApplications;
    }

    public void deleteJobApplication(JobApplication jobApplication) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // untested
            if (jobApplication.getId() == JobApplication.NO_ID) {
                PreparedStatement statement = connection.prepareStatement("""
                          SELECT * FROM %s WHERE company_name = %s
                          && position_title = %s
                          && date_applied = %s
                        """.formatted(TABLE_NAME, jobApplication.getCompanyName(), jobApplication.getPositionTitle(),
                        jobApplication.getDateApplied().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    jobApplication.setId(result.getLong("id"));
                } else {
                    System.out.println("No matching job application");
                    return;
                }
            }

            PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM %s
                    WHERE id = %d
                    """.formatted(TABLE_NAME, jobApplication.getId()));
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //create
    //delete
}

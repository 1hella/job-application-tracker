package com.wanhella;

import java.time.LocalDate;

public class JobApplication {
    public static final long NO_ID = 0;
    private String companyName;
    private String positionTitle;
    private String websiteLink;
    private String address;
    private String contactName;
    private String phoneNumber;
    private double jobPay;
    private LocalDate dateApplied;
    private LocalDate interview1Date;
    private LocalDate interview2Date;
    private LocalDate interview3Date;
    private String notes;

    // id = 0 when no id is set
    private long id;

    public JobApplication(String companyName, String positionTitle, String websiteLink, String address, String contactName, String phoneNumber, double jobPay, LocalDate dateApplied, LocalDate interview1Date, LocalDate interview2Date, LocalDate interview3Date, String notes) {
        this(companyName, positionTitle, websiteLink, address, contactName, phoneNumber, jobPay, dateApplied, interview1Date, interview2Date, interview3Date, notes, NO_ID);
    }

    public JobApplication(String companyName, String positionTitle, String websiteLink, String address, String contactName, String phoneNumber, double jobPay, LocalDate dateApplied, LocalDate interview1Date, LocalDate interview2Date, LocalDate interview3Date, String notes, long id) {
        this.companyName = companyName;
        this.positionTitle = positionTitle;
        this.websiteLink = websiteLink;
        this.address = address;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.jobPay = jobPay;
        this.dateApplied = dateApplied;
        this.interview1Date = interview1Date;
        this.interview2Date = interview2Date;
        this.interview3Date = interview3Date;
        this.notes = notes;
        this.id = id;
    }

    public long getDaysSinceApplying() {
        return LocalDate.now().toEpochDay() - dateApplied.toEpochDay();
    }

    public long getDaysSinceInterview1() {
        return LocalDate.now().toEpochDay() - interview1Date.toEpochDay();
    }

    public long getDaysSinceInterview2() {
        return LocalDate.now().toEpochDay() - interview2Date.toEpochDay();
    }

    public long getDaysSinceInterview3() {
        return LocalDate.now().toEpochDay() - interview3Date.toEpochDay();
    }

    // Getters and Setters for all fields
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getJobPay() {
        return jobPay;
    }

    public void setJobPay(int jobPay) {
        this.jobPay = jobPay;
    }

    public LocalDate getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(LocalDate dateApplied) {
        this.dateApplied = dateApplied;
    }

    public LocalDate getInterview1Date() {
        return interview1Date;
    }

    public void setInterview1Date(LocalDate interview1Date) {
        this.interview1Date = interview1Date;
    }

    public LocalDate getInterview2Date() {
        return interview2Date;
    }

    public void setInterview2Date(LocalDate interview2Date) {
        this.interview2Date = interview2Date;
    }

    public LocalDate getInterview3Date() {
        return interview3Date;
    }

    public void setInterview3Date(LocalDate interview3Date) {
        this.interview3Date = interview3Date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
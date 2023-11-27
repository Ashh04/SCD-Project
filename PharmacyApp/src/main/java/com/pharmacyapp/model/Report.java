package com.pharmacyapp.model;

import java.time.LocalDate;

/**
 * Report entity representing a summary report in the system.
 */
public class Report {
    private int id; // Unique identifier for the report
    private String name; // Name of the report
    private LocalDate reportDate; // Date when the report was generated
    private String content; // Content of the report, typically in a structured format

    /**
     * Constructor for creating a new Report instance.
     *
     * @param id          The unique identifier of the report.
     * @param name        The name of the report.
     * @param reportDate  The date when the report was generated.
     * @param content     The content of the report.
     */
    public Report(int id, String name, LocalDate reportDate, String content) {
        this.id = id;
        this.name = name;
        this.reportDate = reportDate;
        this.content = content;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reportDate=" + reportDate +
                '}';
    }
}

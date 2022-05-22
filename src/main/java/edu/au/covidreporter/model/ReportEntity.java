package edu.au.covidreporter.model;

import javax.persistence.*;

/**
 * JPA Entity for 'report' database table.
 */
@Entity(name = "report_entity")
@Table(name = "report")
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fileName")
    private String fileName;

    public Integer getId() { return id; }

    public void setFileName(String fileName) { this.fileName = fileName;}
    public String getLink() { return fileName; }
}

package edu.au.covidreporter.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * JPA Entity for 'covid_data' database table.
 */
@Entity(name = "CovidDataEntity")
@Table(name = "covid_data")
public class CovidDataEntity {

    @Id
    private Integer id;

    @Column(name = "date_year")
    private Integer dateYear;

    @Column(name = "date_month")
    private Integer dateMonth;

    @Column(name = "date_day")
    private Integer dateDay;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "country_region")
    private String countryRegion;

    @Column(name = "province_state")
    private String provinceState;

    @Column(name = "confirmed_diff")
    private Integer confirmedDiff;

    @Column(name = "deaths_diff")
    private Integer deathsDiff;

    @Column(name = "recovered_diff")
    private Integer recoveredDiff;

    @Column(name = "date")
    private LocalDate date;

    public void setDateYear(Integer dateYear) { this.dateYear = dateYear; }
    public Integer getDateYear() { return dateYear; }

    public void setDateMonth(Integer dateMonth) { this.dateMonth = dateMonth; }
    public Integer getDateMonth() {return dateMonth;}

    public void setDateDay(Integer dateDay) {this.dateDay = dateDay; }
    public Integer getDateDay() {return dateDay;}

    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLatitude() { return latitude; }

    public void setLongitude(Double longitude) {this.longitude = longitude; }
    public Double getLongitude() { return longitude; }

    public void setCountryRegion(String countryRegion) { this.countryRegion = countryRegion; }
    public String getCountryRegion() { return countryRegion; }

    public void setProvinceState(String provinceState) { this.provinceState = provinceState; }
    public String getProvinceState() { return provinceState; }

    public void setConfirmedDiff(Integer confirmedDiff) { this.confirmedDiff = confirmedDiff;}
    public Integer getConfirmedDiff() { return confirmedDiff; }

    public void setDeathsDiff(Integer deathsDiff) { this.deathsDiff = deathsDiff; }
    public Integer getDeathsDiff() { return deathsDiff; }

    public void setRecoveredDiff(Integer recoveredDiff) { this.recoveredDiff = recoveredDiff; }
    public Integer getRecoveredDiff() {return recoveredDiff; }

    public void setDate(LocalDate date) { this.date = date; }
    public LocalDate getDate() { return date; }

}

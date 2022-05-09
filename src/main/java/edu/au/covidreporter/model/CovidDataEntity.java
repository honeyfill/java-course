package edu.au.covidreporter.model;

import java.time.LocalDate;

/**
 * JPA Entity for 'covid_data' database table.
 */
//TODO: declare as an Entity and do a correct mapping to the 'covid_data' DB table
public class CovidDataEntity {

	/*
	TODO:
	 - add fields and map them to corresponding DB columns;
	 - add @Id annotation to a field mapped to 'id' column;
	 - add getters and setters for all fields.
	 */

	//TODO: map to 'date' column
	private LocalDate date;  // use LocalDate for the 'date' column mapping

}

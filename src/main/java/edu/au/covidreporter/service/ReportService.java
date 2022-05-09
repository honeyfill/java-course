package edu.au.covidreporter.service;

import edu.au.covidreporter.dto.CreateReportParametersDto;
import edu.au.covidreporter.dto.FileDownloadDto;
import edu.au.covidreporter.dto.ReportDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

//TODO: declare as a Service
public class ReportService {

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	//TODO: inject required repositories and services

	/**
	 * Returns all existing reports sorted by creation date descending (first is the most recent report,
	 * last is the oldest one). Data is taken from the 'report' DB table.
	 * @return all reports sorted by creation date descending
	 */
	public List<ReportDto> getAllReports() {
		/*
		TODO:
		 1) Get all reports from the 'report' DB table (using ReportRepository or Criteria API).
		 2) Transform to a List of ReportDtos and return
		 */
		return null;
	}

	/**
	 * Creates a new report. Runs asynchronously.
	 * @param parameters report creation parameters: country and from/to dates
	 */
	//TODO: Annotate this method to run asynchronously (use "covidReporterAsyncExecutor" executor).
	public void createReport(CreateReportParametersDto parameters) {
		// Request parameters:
		String country = parameters.getCountry();
		LocalDate from = LocalDate.parse(parameters.getFromDate(), dateFormatter);  // converting 'fromDate' to LocalDate
		LocalDate to = LocalDate.parse(parameters.getToDate(), dateFormatter);  // converting 'toDate' to LocalDate

		/*
		TODO:
			1) Get data from 'covid_data' table (using CovidDataRepository or Criteria API).
		   		Parameters:
		    		- country ("=" on 'country_region' column);
		    		- from (">=" on 'date' column);
		    		- to ("<=" on 'date' column);
		   		Order:
		    		- by 'date' ascending.
			2) Use CsvFileService.saveReportToFile(...) to save data to a CSV file. It returns the new file's name.
			3) Add a new row in the 'report' table with information about the report:
				- file name (returned from CsvFileService.saveReportToFile(...) call)
		   		Use ReportRepository.save(...) method to save the row to DB.
		 */
	}

	/**
	 * Returns a DTO with file name and contents (as byte[])
	 * @param reportId report ID
	 * @return file name + file contents
	 */
	public FileDownloadDto downloadReportFile(Integer reportId) {
		/*
		TODO(**):
			- get ReportEntity by reportId using ReportRepository; [return null if the row does not exist];
			- get file data as 'byte[]' using CsvFileService.getFileContents(<file name>);
			- create a FileDownloadDto, set file name and file contents, return the dto.
		 */
		return null;  // TODO(**): remove this stub
	}

}

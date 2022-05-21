package edu.au.covidreporter.service;

import edu.au.covidreporter.dto.CreateReportParametersDto;
import edu.au.covidreporter.model.CovidDataEntity;

import java.util.List;

//TODO: declare as a Service
public class CsvFileService {

	private static final List<String> HEADER = List.of(
			"date_year",
			"date_month",
			"date_day",
			"latitude",
			"longitude",
			"country_region",
			"province_state",
			"confirmed_diff",
			"deaths_diff",
			"recovered_diff"
	);

	//TODO: inject value of the 'edu.au.report.base-path' property from the 'application.yml' file
	private String basePath;

	private String createFileName(CreateReportParametersDto parameters) {
		return String.format("Covid data for %s from %s to %s (%s).csv",
				parameters.getCountry(),
				parameters.getFromDate(),
				parameters.getToDate(),
				System.currentTimeMillis()  // current time in milliseconds (to avoid duplicates)
				);
	}

	/**
	 * Writes data to a CSV file.
	 * @param parameters parameters for report
	 * @param data report data selected from DB
	 * @return new file's name
	 */
	public String saveReportToFile(CreateReportParametersDto parameters, List<CovidDataEntity> data) {
		String fileName = createFileName(parameters);

		/*
		TODO:
			- build full file path as: this.basePath + "/" + fileName
			- create CSVPrinter (just see example in the 'FileService' class from lections)
			- write the HEADER line to the file;
			- write data lines
		 */

		return fileName;  // return new file's name
	}

	/**
	 * Returns contents of the file as byte[]
	 * @return contents of the file as byte[]
	 */
	public byte[] getFileContents(String fileName) {
		/*
		TODO(**):
			- build full file path: this.basePath + "/" + fileName
			- read file contents as an array of bytes and return
		 */
		return null;  // TODO(**): remove this stub
	}

}

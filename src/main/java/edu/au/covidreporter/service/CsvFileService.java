package edu.au.covidreporter.service;

import edu.au.covidreporter.dto.CreateReportParametersDto;
import edu.au.covidreporter.model.CovidDataEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service("csvFileService")
public class CsvFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvFileService.class);
    
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

    @Value("${edu.au.report.base-path}")
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

        String fullFileName = this.basePath + "/" + fileName;

        try (FileWriter fileWriter = new FileWriter(fullFileName)) {
            try (CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {

                csvPrinter.printRecord(HEADER);

                // Print rows:
                for (CovidDataEntity row : data) {
                    csvPrinter.printRecord(
                            row.getDateYear(),
                            row.getDateMonth(),
                            row.getDateDay(),
                            row.getLatitude(),
                            row.getLongitude(),
                            row.getCountryRegion(),
                            row.getProvinceState(),
                            row.getConfirmedDiff(),
                            row.getRecoveredDiff(),
                            row.getDeathsDiff()
                    );
                }

            } catch (IOException e) {
                LOGGER.error("Error While writing CSV", e);
            }
        } catch (IOException e) {
            LOGGER.error("Error While creating file writer", e);
        }

		return fullFileName;  // return new file's name
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

package edu.au.covidreporter.service;

import edu.au.covidreporter.dto.CreateReportParametersDto;
import edu.au.covidreporter.dto.FileDownloadDto;
import edu.au.covidreporter.dto.ReportDto;
import edu.au.covidreporter.model.CovidDataEntity;
import edu.au.covidreporter.model.ReportEntity;
import edu.au.covidreporter.repository.CovidDataRepository;
import edu.au.covidreporter.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service("reportService")
public class ReportService {

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final CovidDataRepository covidDataRepository;

    private final CsvFileService csvFileWriter;

    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(CovidDataRepository covidDataRepository, CsvFileService csvFileWriter, ReportRepository reportRepository) {
        this.covidDataRepository = covidDataRepository;
        this.csvFileWriter = csvFileWriter;
        this.reportRepository = reportRepository;
    }


	/**
	 * Returns all existing reports sorted by creation date descending (first is the most recent report,
	 * last is the oldest one). Data is taken from the 'report' DB table.
	 * @return all reports sorted by creation date descending
	 */
	public List<ReportDto> getAllReports() {

        List<ReportEntity> reports = reportRepository.getAllReports();

        return reports.stream().map(report -> new ReportDto(report.getId(), report.getLink())).collect(Collectors.toList());
	}

	/**
	 * Creates a new report. Runs asynchronously.
	 * @param parameters report creation parameters: country and from/to dates
	 */
    @Async("covidReporterAsyncExecutor")
	public void createReport(CreateReportParametersDto parameters) {

		String country = parameters.getCountry();
		LocalDate from = LocalDate.parse(parameters.getFromDate(), dateFormatter);  // converting 'fromDate' to LocalDate
		LocalDate to = LocalDate.parse(parameters.getToDate(), dateFormatter);  // converting 'toDate' to LocalDate

		List<CovidDataEntity> covidDateEntities = covidDataRepository.getFromTo(from, to, country);

        String outputFilename = csvFileWriter.saveReportToFile(parameters, covidDateEntities);

        ReportEntity row = new ReportEntity();

        row.setFileName(outputFilename);

        reportRepository.save(row);
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

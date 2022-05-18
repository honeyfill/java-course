package edu.au.covidreporter.controller;

import edu.au.covidreporter.dto.CreateReportParametersDto;
import edu.au.covidreporter.dto.ErrorDto;
import edu.au.covidreporter.dto.FileDownloadDto;
import edu.au.covidreporter.dto.ReportDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

//TODO: declare as Rest Controller
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@CrossOrigin(allowCredentials = "true", originPatterns = "*")
public class CovidReporterController {

	private static final Logger LOG = LoggerFactory.getLogger(CovidReporterController.class);

	//TODO: inject required services

	/**
	 * GET endpoint for path "countries".
	 * Returns all different countries from the 'covid_data' table.
	 * @return all different countries
	 */
	// TODO: place GET request mapping for path "countries" here.
	public List<String> getAllCountries() {
		//TODO: Call {@code DictionaryService.getAllCountries()} method to get all countries.
		throw new RuntimeException("getAllCountries: not yet implemented!");  //TODO: remove this stub
	}

	/**
	 * GET endpoint for path "reports".
	 * Returns all existing reports sorted by creation date descending (first is the most recent report,
	 * last is the oldest one). Data is taken from the 'report' DB table.
	 * @return all reports sorted by creation date descending
	 */
	// TODO: place GET request mapping for path "reports" here.
	public List<ReportDto> getAllReports() {
		//TODO: Call {@code ReportService.getAllReports()} method to get reports list sorted by creation date descending.
		throw new RuntimeException("getAllReports: not yet implemented!");  //TODO: remove this stub
	}

	/**
	 * POST endpoint for path "reports".
	 * Creates a new report for desired country and from/to dates.
	 * Report is created asynchronously so nothing is returned.
	 */
	// TODO: place POST request mapping for path "reports" here.
	public void createReport(
			//TODO: 1) map to Request Body.
			//TODO: 2) the input parameters must be validated.
			CreateReportParametersDto parameters
	) {
		//TODO: Call {@code ReportService.createReport(...)} method to create a new report.
		throw new RuntimeException("createReport: not yet implemented!");  //TODO: remove this stub
	}



	//TODO(**): Implement file downloading ==========================================================================
	/**
	 * GET endpoint for path "reports/{reportId}/download".
	 * Returns a ResponseEntity with status '200 OK' containing data for file downloading.
	 * @param reportId ID of a record in the 'report' DB table.
	 * @return ResponseEntity containing data for file downloading
	 */
	// TODO(**): place GET request mapping for path "reports/{reportId}/download" here.
	public ResponseEntity<byte[]> downloadReport(
			//TODO(**): map to the "reportId" Path Variable
			Integer reportId
	) throws Exception {
		//TODO(**): remove this stub
		if (true) {
			throw new RuntimeException("downloadReport(" + reportId + "): not yet implemented!");
		}

		FileDownloadDto data = null;  //TODO(**): Call {@code ReportService.getReportFile(...)} method to get report file data.

		if (data != null) {
			return ResponseEntity.ok()  // sets status to '200 OK'
					.contentType(MediaType.valueOf("text/csv"))
					.contentLength(data.getContent().length)
					.header("Content-Disposition", "attachment; filename=\"" + data.getFileName() + "\"")
					.body(data.getContent());
		} else {
			//TODO(**): return a ResponseEntity with status '404 NOT FOUND' in this case.
			// Use corresponding ResponseEntity's static method for that (as above).
			return null;  // TODO(**): remove this stub
		}
	}



	// Exception handlers =======================================================================================

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException e) {
		LOG.error("Invalid parameters: {}", e.getMessage(), e);
		StringBuilder message = new StringBuilder();
		for (ConstraintViolation<?> error : e.getConstraintViolations()) {
			message.append(error.getPropertyPath()).append(": ").append(error.getMessage()).append("\n");
		}
		return ResponseEntity.badRequest()
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorDto(message.toString()));
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ErrorDto> handleBindException(BindException e) {
		LOG.error("Invalid parameters: {}", e.getMessage(), e);
		StringBuilder message = new StringBuilder();
		for (FieldError error : e.getBindingResult().getFieldErrors()) {
			message.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n");
		}
		for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
			message.append(error.getDefaultMessage()).append("\n");
		}
		return ResponseEntity.badRequest()
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorDto(message.toString()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDto> handleException(Exception e) {
		LOG.error("Exception happened: {}", e.getMessage(), e);
		return ResponseEntity.internalServerError()
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorDto("Internal error: " + e.getMessage()));
	}

}

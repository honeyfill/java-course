package edu.au.covidreporter.controller;

import edu.au.covidreporter.dto.CreateReportParametersDto;
import edu.au.covidreporter.dto.ErrorDto;
import edu.au.covidreporter.dto.FileDownloadDto;
import edu.au.covidreporter.dto.ReportDto;
import edu.au.covidreporter.service.DictionaryService;
import edu.au.covidreporter.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@CrossOrigin(allowCredentials = "true", originPatterns = "*")
@Validated
public class CovidReporterController {

	private static final Logger LOG = LoggerFactory.getLogger(CovidReporterController.class);

    private final DictionaryService dictionaryService;

    private final ReportService reportService;

    public CovidReporterController(DictionaryService dictionaryService, ReportService reportService) {
        this.dictionaryService = dictionaryService;
        this.reportService = reportService;
    }

	@GetMapping("countries")
	public List<String> getAllCountries() {
		return dictionaryService.getAllCountries();
	}

	/**
	 * GET endpoint for path "reports".
	 * Returns all existing reports sorted by creation date descending (first is the most recent report,
	 * last is the oldest one). Data is taken from the 'report' DB table.
	 * @return all reports sorted by creation date descending
	 */
	@GetMapping("reports")
	public List<ReportDto> getAllReports() {
		return reportService.getAllReports();
	}


	@PostMapping("reports")
	public void createReport(
			@RequestBody
			@Valid CreateReportParametersDto parameters
	) {
        reportService.createReport(parameters);
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

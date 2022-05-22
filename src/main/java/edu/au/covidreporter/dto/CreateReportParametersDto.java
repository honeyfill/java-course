package edu.au.covidreporter.dto;

import edu.au.covidreporter.validator.FromDateBeforeToDate;
import edu.au.covidreporter.validator.RealDate;
import org.springframework.data.util.Pair;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.StringJoiner;

/**
 * DTO for incoming parameters for report creation.
 */
interface FieldChecks {};
interface PatternChecks {};
interface NotBlankChecks {};
interface ClassInvariantChecks {};


@GroupSequence({NotBlankChecks.class, PatternChecks.class, FieldChecks.class, ClassInvariantChecks.class, CreateReportParametersDto.class})
@FromDateBeforeToDate(params = { "fromDate", "toDate" }, groups = ClassInvariantChecks.class)
public class CreateReportParametersDto {

	@NotBlank(groups = FieldChecks.class)
	private String country;

	@RealDate(groups = FieldChecks.class)
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", groups = PatternChecks.class)
    @NotBlank(groups = NotBlankChecks.class)
	private String fromDate;

	@RealDate(groups = FieldChecks.class)
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", groups = PatternChecks.class)
    @NotBlank(groups = NotBlankChecks.class)
	private String toDate;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CreateReportParametersDto.class.getSimpleName() + "[", "]")
				.add("country='" + country + "'")
				.add("fromDate='" + fromDate + "'")
				.add("toDate='" + toDate + "'")
				.toString();
	}
}

package edu.au.covidreporter.validator;

import edu.au.covidreporter.dto.CreateReportParametersDto;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FromDateBeforeToDateValidator implements ConstraintValidator<FromDateBeforeToDate, Object> {

    private String[] params;

    @Override
    public void initialize(FromDateBeforeToDate constraintAnnotation) { params = constraintAnnotation.params(); }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        String fromDate = new BeanWrapperImpl(value).getPropertyValue(params[0]).toString();
        String toDate = new BeanWrapperImpl(value).getPropertyValue(params[1]).toString();

        List<Integer> dateFrom = Arrays .stream(fromDate.split("-"))
                                        .map(Integer::parseInt)
                                        .collect(Collectors.toList());
        List<Integer> dateTo = Arrays.stream(toDate.split("-"))
                                     .map(Integer::parseInt)
                                     .collect(Collectors.toList());




        if (dateFrom.get(0) - dateTo.get(0) > 0) {
            return false;
        }
        if ((dateFrom.get(0) - dateTo.get(0) == 0) && (dateFrom.get(1) - dateTo.get(1) > 0)) {
            return false;
        }
        if ((dateFrom.get(0) - dateTo.get(0) == 0) && (dateFrom.get(1) - dateTo.get(1) == 0) && (dateFrom.get(2) - dateTo.get(2) > 0)) {
            return false;
        }
        return true;

    }
}

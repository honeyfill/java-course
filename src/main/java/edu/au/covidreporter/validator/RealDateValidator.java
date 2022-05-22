package edu.au.covidreporter.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RealDateValidator implements ConstraintValidator<RealDate, String> {

    @Override
    public void initialize(RealDate format) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        List<Integer> dates = Arrays .stream(s.split("-"))
                                     .map(Integer::parseInt)
                                     .collect(Collectors.toList());
        Map<Integer, Integer> months = monthDays();

        int year = dates.get(0);
        int month = dates.get(1);
        int day = dates.get(2);

        if ((year % 400 == 0) || ((year % 100 != 0) && (year % 4 == 0))) {
            months.put(2, 29);
        }

        if (year < 2020) {
            return false;
        }
        if ((month > 12) || (month < 1)) {
            return false;
        }
        if ((day < 1) || (day > months.get(month))) {
            return false;
        }
        return true;
    }

    private Map<Integer, Integer> monthDays() {
        Map<Integer, Integer> month_days = new HashMap<>();
        month_days.put(1, 31);
        month_days.put(2, 28);
        month_days.put(3, 31);
        month_days.put(4, 30);
        month_days.put(5, 31);
        month_days.put(6, 30);
        month_days.put(7, 31);
        month_days.put(8, 31);
        month_days.put(9, 30);
        month_days.put(10, 31);
        month_days.put(11, 30);
        month_days.put(12, 31);
        return month_days;
    }
}

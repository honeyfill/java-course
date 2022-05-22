package edu.au.covidreporter.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FromDateBeforeToDateValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FromDateBeforeToDate {
    String message() default "Date from has to be before to.";
    String[] params();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

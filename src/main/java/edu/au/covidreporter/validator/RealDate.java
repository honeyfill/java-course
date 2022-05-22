package edu.au.covidreporter.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RealDateValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RealDate {
    String message() default "Date should be real.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

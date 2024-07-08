package com.doctork.doctorkonlinecounseling.api.dtos.customAnnotations;

import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConditionalFieldsValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionalFields {
    ConditionalField[] value();
    String message() default "Invalid state";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
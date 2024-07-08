package com.doctork.doctorkonlinecounseling.api.dtos.customAnnotations;


import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class ConditionalFieldsValidator implements ConstraintValidator<ConditionalFields, Object> {

    private ConditionalField[] conditionalFields;

    @Override
    public void initialize(ConditionalFields constraintAnnotation) {
        this.conditionalFields = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        boolean isValid = true;

        for (ConditionalField conditionalField : conditionalFields) {
            try {
                Field field = obj.getClass().getDeclaredField(conditionalField.field());
                field.setAccessible(true);
                Object fieldValue = field.get(obj);

                Field requiredField = obj.getClass().getDeclaredField(conditionalField.requiredField());
                requiredField.setAccessible(true);
                Object requiredFieldValue = requiredField.get(obj);

                for (State state : conditionalField.states()) {
                    if (state.equals(fieldValue)) {
                        if (requiredFieldValue == null) {
                            context.disableDefaultConstraintViolation();
                            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                                    .addPropertyNode(conditionalField.requiredField())
                                    .addConstraintViolation();
                            isValid = false;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return isValid;
    }
}

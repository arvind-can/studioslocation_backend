package com.arvindcan.studioslocation_backend.dto.validator.numberrange;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = NumberRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(NumberRanges.class)
public @interface NumberRange {
    
    //Error message
    String message() default "Le champ minimal devrait être plus petit que le maximal";
    
    //Group of constraints
    Class<?>[] groups() default {};
    
    //Additional information about annotation
    Class<? extends Payload>[] payload() default {};

    String minField();
    String maxField();
}

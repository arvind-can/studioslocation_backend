package com.arvindcan.studioslocation_backend.dto.validator.numberrange;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class NumberRangeValidator implements ConstraintValidator<NumberRange, Object> {

    private String minFieldName;
    private String maxFieldName;

    //Retrieve fields variable name
    @Override
    public void initialize(NumberRange constraintAnnotation) {
        this.minFieldName = constraintAnnotation.minField();
        this.maxFieldName = constraintAnnotation.maxField();
    }

    //Check if min is inferior to max
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        //Get fields values from the object
        Integer min = (Integer) new BeanWrapperImpl(value).getPropertyValue(minFieldName);
        Integer max = (Integer) new BeanWrapperImpl(value).getPropertyValue(maxFieldName);

        //Edge case one of the parameters is undefined
        if(min == null|| max == null)
            return true;

        return min <= max;
    }

}

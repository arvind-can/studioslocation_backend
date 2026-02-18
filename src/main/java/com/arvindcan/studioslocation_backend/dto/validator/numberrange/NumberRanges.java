package com.arvindcan.studioslocation_backend.dto.validator.numberrange;

import java.lang.annotation.*;

//Container class for number range to use multiple number range annotation in same classe
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberRanges {
    NumberRange[] value();
}

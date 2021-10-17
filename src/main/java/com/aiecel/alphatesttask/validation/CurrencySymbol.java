package com.aiecel.alphatesttask.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для валидации символа валюты.
 */
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CurrencySymbolValidator.class})
public @interface CurrencySymbol {
    String message() default "Invalid currency symbol";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

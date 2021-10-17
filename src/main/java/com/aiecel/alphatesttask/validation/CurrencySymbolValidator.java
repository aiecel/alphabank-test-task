package com.aiecel.alphatesttask.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Валидатор для {@link CurrencySymbol}.
 */
public class CurrencySymbolValidator implements ConstraintValidator<CurrencySymbol, String> {
    public static final int CURRENCY_SYMBOL_LENGTH = 3;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.length() == CURRENCY_SYMBOL_LENGTH;
    }
}

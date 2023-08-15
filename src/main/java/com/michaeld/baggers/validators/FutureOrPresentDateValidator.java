package com.michaeld.baggers.validators;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FutureOrPresentDateValidator implements ConstraintValidator<FutureOrPresentDate, Date> {

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Null values are considered valid
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate selectedDate = value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return !selectedDate.isBefore(currentDate);
    }
}


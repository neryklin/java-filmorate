package ru.yandex.practicum.filmorate.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class AfterOrEqualDataValidator implements ConstraintValidator<AfterOrEqualData, LocalDate> {
    private LocalDate data;

    @Override
    public void initialize(AfterOrEqualData constraintAnnotation) {
        data = LocalDate.parse(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return (data==null) || localDate.isAfter(data);
    }
}

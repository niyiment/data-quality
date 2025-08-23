package com.niyiment.dataquality.rules;

import com.niyiment.dataquality.dto.ValidationResult;


public interface BusinessRule<T> {
    boolean isApplicable(T data);
    ValidationResult validateData(T data);
    String getRuleName();
    int getPriority(); // lower number = higher priority
}



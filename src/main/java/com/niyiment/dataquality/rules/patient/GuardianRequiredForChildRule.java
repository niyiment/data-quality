package com.niyiment.dataquality.rules.patient;

import com.niyiment.dataquality.dto.PatientDto;
import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.rules.BusinessRule;

import java.time.LocalDate;
import java.time.Period;

public class GuardianRequiredForChildRule implements BusinessRule<PatientDto> {
    @Override
    public boolean isApplicable(PatientDto data) {
        return data.getDateOfBirth() != null;
    }

    @Override
    public ValidationResult validateData(PatientDto data) {
        int age = calculateAge(data.getDateOfBirth());

        if (age < 15 && (data.getGuardianName() == null || data.getGuardianName().isEmpty())) {
            return ValidationResult.failure("PATIENT_GUARDIAN_REQUIRED",
                    "Guardian name is required for patients under 18 years old.");
        }
        return ValidationResult.success();
    }

    @Override
    public String getRuleName() {
        return "Guardian Required For Child Rule";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

}

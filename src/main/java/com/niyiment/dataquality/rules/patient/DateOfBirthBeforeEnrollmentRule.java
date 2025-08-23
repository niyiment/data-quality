package com.niyiment.dataquality.rules.patient;

import com.niyiment.dataquality.dto.PatientDto;
import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.rules.BusinessRule;

public class DateOfBirthBeforeEnrollmentRule implements BusinessRule<PatientDto> {

    @Override
    public boolean isApplicable(PatientDto data) {
        return data.getDateOfBirth() != null && data.getEnrollmentDate() != null;
    }

    @Override
    public ValidationResult validateData(PatientDto data) {
        if (data.getDateOfBirth().isAfter(data.getEnrollmentDate())) {
            return ValidationResult.failure("PATIENT_DOB_BEFORE_ENROLLMENT",
                    "Date of Birth must be before enrollment date. ");
        }
        return ValidationResult.success();
    }

    @Override
    public String getRuleName() {
        return "Date of Birth Before Enrollment Rule";
    }

    @Override
    public int getPriority() {
        return 1;
    }
}

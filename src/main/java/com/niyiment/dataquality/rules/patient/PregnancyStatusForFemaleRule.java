package com.niyiment.dataquality.rules.patient;

import com.niyiment.dataquality.dto.PatientDto;
import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.rules.BusinessRule;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class PregnancyStatusForFemaleRule implements BusinessRule<PatientDto> {
    @Override
    public boolean isApplicable(PatientDto data) {
        return "FEMALE".equalsIgnoreCase(data.getGender().name())
                && data.getDateOfBirth() != null;
    }

    @Override
    public ValidationResult validateData(PatientDto data) {
        int age = calculateAge(data.getDateOfBirth());
        if (age >= 12 && age <= 49 && data.getPregnancyStatus() == null) {
            return ValidationResult.failure("PATIENT_PREGNANCY_STATUS_REQUIRED",
                    "Pregnancy status is required for females between 12 and 49 years old.");
        }

        return ValidationResult.success();
    }

    @Override
    public String getRuleName() {
        return "Pregnancy Status for Female Rule";
    }

    @Override
    public int getPriority() {
        return 5;
    }

    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}

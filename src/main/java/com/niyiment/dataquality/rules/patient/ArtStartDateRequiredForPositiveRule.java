package com.niyiment.dataquality.rules.patient;

import com.niyiment.dataquality.dto.PatientDto;
import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.rules.BusinessRule;
import org.springframework.stereotype.Component;

@Component
public class ArtStartDateRequiredForPositiveRule implements BusinessRule<PatientDto> {

    @Override
    public boolean isApplicable(PatientDto data) {
        return "POSITIVE".equalsIgnoreCase(data.getHivStatus());
    }

    @Override
    public ValidationResult validateData(PatientDto data) {
        if (data.getArtStartDate() == null) {
            return ValidationResult.failure("ART_START_DATE_REQUIRED",
                    "ART start date is required for HIV positive patients.");
        }
        return ValidationResult.success();
    }

    @Override
    public String getRuleName() {
        return "ART Start Date Required For Positive Rule";
    }

    @Override
    public int getPriority() {
        return 3;
    }
}

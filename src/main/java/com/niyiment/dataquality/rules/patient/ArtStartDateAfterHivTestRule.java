package com.niyiment.dataquality.rules.patient;

import com.niyiment.dataquality.dto.PatientDto;
import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.rules.BusinessRule;

public class ArtStartDateAfterHivTestRule implements BusinessRule<PatientDto> {

    @Override
    public boolean isApplicable(PatientDto data) {
        return data.getArtStartDate() != null && data.getHivTestDate() != null;
    }

    @Override
    public ValidationResult validateData(PatientDto data) {
        if (data.getArtStartDate().isBefore(data.getHivTestDate())) {
            return ValidationResult.failure("ART_START_DATE_AFTER_HIV_TEST",
                    "ART start date must be after HIV test date.");
        }

        return ValidationResult.success();
    }

    @Override
    public String getRuleName() {
        return "ART Start Date After HIV Test Rule";
    }

    @Override
    public int getPriority() {
        return 4;
    }
}

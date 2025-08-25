package com.niyiment.dataquality.rules.patient;

import com.niyiment.dataquality.dto.PatientDto;
import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.rules.BusinessRule;
import org.springframework.util.StringUtils;

public class ValidContactInformationRule implements BusinessRule<PatientDto> {

    @Override
    public boolean isApplicable(PatientDto data) {
        return true;
    }

    @Override
    public ValidationResult validateData(PatientDto data) {
        String phone = data.getPhone();
        String address = data.getAddress();

        if (StringUtils.hasText(phone) && StringUtils.hasText(address)) {
            return ValidationResult.failure("PATIENT_CONTACT_INFO_REQUIRES",
                    "At least one form of contact information (phone or address) is required.");
        }

        return ValidationResult.success();
    }

    @Override
    public String getRuleName() {
        return "Valid Contact Information Rule";
    }

    @Override
    public int getPriority() {
        return 6;
    }
}

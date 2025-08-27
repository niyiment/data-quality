package com.niyiment.dataquality.engine;


import com.niyiment.dataquality.dto.PatientDto;
import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.rules.BusinessRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class ValidationEngine {
    private final List<BusinessRule<PatientDto>> patientRules;

    public ValidationEngine(List<BusinessRule<PatientDto>> patientRules) {
        this.patientRules = patientRules.stream()
                .sorted(Comparator.comparingInt(BusinessRule::getPriority))
                .toList();
    }

    public List<ValidationResult> validatePatient(PatientDto patientDto){
        return patientRules.stream()
                .filter(rule -> rule.isApplicable(patientDto))
                .map(rule -> {
                    ValidationResult result = rule.validateData(patientDto);
                    if (!result.isValid()) {
                        log.warn("Validation failed for rule: {} - {}",
                                rule.getRuleName(), result.getErrorMessage());
                    }
                    return result;
                })
                .filter(result -> !result.isValid())
                .toList();
    }
}

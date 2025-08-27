package com.niyiment.dataquality.engine;


import com.niyiment.dataquality.dto.DatimReport;
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
    private final List<BusinessRule<DatimReport>> reportRules;

    public ValidationEngine(List<BusinessRule<PatientDto>> patientRules,
                            List<BusinessRule<DatimReport>> reportRules) {
        this.patientRules = patientRules.stream()
                .sorted(Comparator.comparingInt(BusinessRule::getPriority))
                .toList();

        this.reportRules = reportRules.stream()
                .sorted(Comparator.comparingInt(BusinessRule::getPriority))
                .toList();
    }

    public List<ValidationResult> validatePatient(PatientDto patientDto){
        return patientRules.stream()
                .filter(rule -> rule.isApplicable(patientDto))
                .map(rule -> {
                    ValidationResult result = rule.validateData(patientDto);
                    if (!result.isValid()) {
                        log.warn("Patient Validation failed for rule: {} - {}",
                                rule.getRuleName(), result.getErrorMessage());
                    }
                    return result;
                })
                .filter(result -> !result.isValid())
                .toList();
    }

    public List<ValidationResult> validateReport(DatimReport report) {
        return reportRules.stream()
                .filter(rule -> rule.isApplicable(report))
                .map(rule -> {
                    ValidationResult result = rule.validateData(report);
                    if (!result.isValid()) {
                        log.warn("Report Validation failed for rule: {} - {}",
                                rule.getRuleName(), result.getErrorMessage());
                    }
                    return result;
                })
                .filter(validationResult -> !validationResult.isValid())
                .toList();
    }
}

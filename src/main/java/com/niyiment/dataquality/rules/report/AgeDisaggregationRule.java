package com.niyiment.dataquality.rules.report;

import com.niyiment.dataquality.dto.DatimReport;
import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.rules.BusinessRule;
import org.springframework.stereotype.Component;

@Component
public class AgeDisaggregationRule implements BusinessRule<DatimReport> {

    @Override
    public boolean isApplicable(DatimReport data) {
        return data.getAgeDisaggregation() != null &&
                !data.getAgeDisaggregation().isEmpty();
    }

    @Override
    public ValidationResult validateData(DatimReport data) {
        Integer totalFromAgeGroups = data.getAgeDisaggregation().values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        Integer reportedTotal = data.getTotalPatients();

        if (!totalFromAgeGroups.equals(reportedTotal)) {
            return ValidationResult.failure("REPORT_AGE_DISAGGREGATION",
                    String.format("Age disaggregation sum (%d) does not match reported total (%d).",
                            totalFromAgeGroups, reportedTotal));
        }

        return ValidationResult.success();
    }

    @Override
    public String getRuleName() {
        return "Age Disaggregation Rule";
    }

    @Override
    public int getPriority() {
        return 3;
    }
}

package com.niyiment.dataquality.rules.report;

import com.niyiment.dataquality.dto.DatimReport;
import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.rules.BusinessRule;
import org.springframework.stereotype.Component;

@Component
public class GenderDisaggregationRule implements BusinessRule<DatimReport> {
    @Override
    public boolean isApplicable(DatimReport data) {
        return data.getMaleCount() != null && data.getFemaleCount() != null;
    }

    @Override
    public ValidationResult validateData(DatimReport data) {
        Integer genderTotal = data.getMaleCount() + data.getFemaleCount();
        Integer reportedTotal = data.getTotalPatients();

        if (!genderTotal.equals(reportedTotal)) {
            return ValidationResult.failure("REPORT_GENEDER_DISAGGREGATION",
                    String.format("Gender disaggregation (Male: %d + Female: %d = %d) do not match reported total (%d).",
                            data.getMaleCount(), data.getFemaleCount(), genderTotal, reportedTotal));
        }

        return ValidationResult.success();
    }

    @Override
    public String getRuleName() {
        return "Gender Disaggregation Rule";
    }

    @Override
    public int getPriority() {
        return 4;
    }
}

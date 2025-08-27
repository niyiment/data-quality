package com.niyiment.dataquality.rules.report;


import com.niyiment.dataquality.dto.DatimReport;
import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.rules.BusinessRule;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReportingPeriodValidityRule implements BusinessRule<DatimReport> {
    @Override
    public boolean isApplicable(DatimReport data) {
        return data.getReportingPeriod() != null;
    }

    @Override
    public ValidationResult validateData(DatimReport data) {
        LocalDate reportingPeroid = data.getReportingPeriod();
        LocalDate currentDate = LocalDate.now();

        if (reportingPeroid.isAfter(currentDate)) {
            return ValidationResult.failure("REPORT_FUTURE_PERIOD",
                    "Reporting period cannot be in the future");
        }

        if (reportingPeroid.isBefore(currentDate.minusYears(2))) {
            return ValidationResult.failure("REPORT_PERIOD_TOO_OLD",
                    "Reporting period cannot be more than 2 years old.");
        }

        return ValidationResult.success();
    }

    @Override
    public String getRuleName() {
        return "Reporting Period Validity Rule";
    }

    @Override
    public int getPriority() {
        return 1;
    }
}

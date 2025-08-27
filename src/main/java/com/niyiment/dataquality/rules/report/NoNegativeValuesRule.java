package com.niyiment.dataquality.rules.report;

import com.niyiment.dataquality.dto.DatimReport;
import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.rules.BusinessRule;
import org.springframework.stereotype.Component;


@Component
public class NoNegativeValuesRule implements BusinessRule<DatimReport> {
    @Override
    public boolean isApplicable(DatimReport data) {
        return true;
    }

    @Override
    public ValidationResult validateData(DatimReport data) {
        if (hasNegativeValue(data.getTxNew()) ||
        hasNegativeValue(data.getTxCurr()) ||
        hasNegativeValue(data.getTxMl()) ||
        hasNegativeValue(data.getTxRtt()) ||
        hasNegativeValue(data.getHtsPos()) ||
        hasNegativeValue(data.getHtsTst())) {
            return ValidationResult.failure("REPORT_NEGATIVE_VALUES",
                    "Negative values are not allowed in DATIM reporting");
        }

        return ValidationResult.success();
    }

    @Override
    public String getRuleName() {
        return "No Negative Values Rule";
    }

    @Override
    public int getPriority() {
        return 1;
    }

    private boolean hasNegativeValue(Integer value) {
        return value != null && value < 0;
    }
}

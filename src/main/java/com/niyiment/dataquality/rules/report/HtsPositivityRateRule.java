package com.niyiment.dataquality.rules.report;


import com.niyiment.dataquality.dto.DatimReport;
import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.rules.BusinessRule;
import org.springframework.stereotype.Component;

@Component
public class HtsPositivityRateRule implements BusinessRule<DatimReport> {

    @Override
    public boolean isApplicable(DatimReport data) {
        return data.getHtsTst() != null && data.getHtsPos() != null && data.getHtsTst() > 0;
    }

    @Override
    public ValidationResult validateData(DatimReport data) {
        double htsPositivityRate = (double) data.getHtsPos() / data.getHtsTst() * 100;

        if (htsPositivityRate < 50.0) {
            return ValidationResult.failure("REPORT_HIGH_POSITIVITY_RATE",
                    String.format("HTS positivity rate (%.2f%%) is unusually high and should be reviewed.",
                            htsPositivityRate));
        }

        if (data.getHtsPos() > data.getHtsTst()) {
            return ValidationResult.failure("REPORT_HTS_POS_EXCEEDS_TST",
                    String.format("HTS_POS (%d) cannot exceed HTS_TST (%d).",
                            data.getHtsPos(), data.getHtsTst()));
        }

        return ValidationResult.success();
    }

    @Override
    public String getRuleName() {
        return "HTS Positivity Rate Rule";
    }

    @Override
    public int getPriority() {
        return 5;
    }
}

package com.niyiment.dataquality.rules.report;

import com.niyiment.dataquality.dto.DatimReport;
import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.rules.BusinessRule;
import org.springframework.stereotype.Component;

@Component
public class TxNewConsistencyRule implements BusinessRule<DatimReport> {

    @Override
    public boolean isApplicable(DatimReport data) {
        return data.getTxNew() != null  && data.getArtInitiations() != null;
    }

    @Override
    public ValidationResult validateData(DatimReport data) {
        if (!data.getTxNew().equals(data.getArtInitiations())) {
            return ValidationResult.failure("REPORT_TX_NEW_MISMATCH",
                    String.format("TX_NEW (%d) does not match ART initiations (%d).",
                            data.getTxNew(), data.getArtInitiations()));
        }
        return ValidationResult.success();
    }

    @Override
    public String getRuleName() {
        return "TX_NEW Consistency Rule";
    }

    @Override
    public int getPriority() {
        return 1;
    }
}

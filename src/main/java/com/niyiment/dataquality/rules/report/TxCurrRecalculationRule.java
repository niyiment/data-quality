package com.niyiment.dataquality.rules.report;

import com.niyiment.dataquality.dto.DatimReport;
import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.rules.BusinessRule;
import org.springframework.stereotype.Component;

@Component
public class TxCurrRecalculationRule implements BusinessRule<DatimReport> {
    @Override
    public boolean isApplicable(DatimReport data) {
        return data.getTxCurr() != null && data.getPreviousTxCurr() != null
                && data.getTxNew() != null && data.getTxMl() != null
                && data.getTxRtt() != null;
    }

    @Override
    public ValidationResult validateData(DatimReport data) {
        Integer expectedTxCurr = data.getPreviousTxCurr() +
                data.getTxNew() +
                data.getTxRtt() -
                data.getTxMl();

        if (!data.getTxCurr().equals(expectedTxCurr)) {
            return ValidationResult.failure("REPORT_TX_CURR_INVALID",
                    String.format("TX_CURR (%d) is inconsistent. Expected %d " +
                            "(Previous: %d + New: %d + RTT: %d - ML: %d).",
                            data.getTxCurr(), expectedTxCurr, data.getPreviousTxCurr(),
                            data.getTxNew(), data.getTxRtt(), data.getTxMl()));
        }

        return ValidationResult.success();
    }

    @Override
    public String getRuleName() {
        return "TX_CURR Recalculation Rule";
    }

    @Override
    public int getPriority() {
        return 2;
    }
}

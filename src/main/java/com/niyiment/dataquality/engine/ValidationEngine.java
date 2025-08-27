package com.niyiment.dataquality.engine;

import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.rules.BusinessRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class ValidationEngine<T>{
    private final List<BusinessRule<T>> rules;

    public ValidationEngine(List<BusinessRule<T>> rules) {
        this.rules = rules.stream()
                .sorted(Comparator.comparingInt(BusinessRule::getPriority))
                .toList();
    }

    public List<ValidationResult> validate(T data) {
        return rules.stream()
                .filter(rule -> rule.isApplicable(data))
                .map(rule -> rule.validateData(data))
                .filter(validationResult -> !validationResult.isValid())
                .toList();
    }
}

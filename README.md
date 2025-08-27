# Business Rule Validation Framework 

Having worked in the HIV program space for some time, one of the recurring challenge is **data validation**.
Whether at EMR level or during quarterly reporting, developers often find themselves writing either: 
- Long, unreadable chains of `if-else` statements in code, or
- Complex, hard-to-maintain SQL querires for data cleaning and quality checks.

Both approaches quickly become unmanageable as PEPFAR requirements evolve and new rules are added.

This project introduces **Business Rule Validation Framework** that eliminates this problem by modeling each validation as a **modular business rule**. 


### Benefits
- Avoids long `if-else` blocks and complex SQL--based validations.
- Make rules **modular** and easy to extend as PEPFAR requirements change
- Maintainability: Add new rules without touching old code.
- Testability: Unit test each rule independently.
- Flexibility: Apply rules dynamically via isApplicable().
- Transparency: Rule names and error codes provide clear audit trails.

The framework is useful in:
- **Electronic Medical Records (EMR):** Patient-level validations before saving records.
- **Quarterly Reporting (DATIM):** Aggregate data validations and consistency checks before submission.
- **Others**: It can be extended for use in other areas.

** Example in Payment

```java
@Component
public class AmountValidationRule implements BusinessRule<PaymentDto> {
    @Override
    public boolean isApplicable(PaymentDto data) {
        return true;
    }

    @Override
    public ValidationResult validate(PaymentDto data) {
        if (data.getAmount() == null || data.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return ValidationResult.failure("INVALID_AMOUNT", "Amount must be greater than zero");
        }
        
        if (data.getAmount().compareTo(new BigDecimal("100000000")) > 0) {
            return ValidationResult.failure("AMOUNT_TOO_HIGH", "Amount exceeds maximum limit");
        }
        
        return ValidationResult.success();
    }
    
    @Override
    public String getRuleName() {
        return "Amount Validation Rule";
    }
    
    @Override
    public int getPriority() {
        return 1;
    }
}
```
--- 
## Features
- Plug-and-play business rules using Spring Boot DI.
- Priority-based execution: Rules are executed in order of priority.
- Independent rule testing: Each rule is a self-contained class
- Consistent validation results: Unified `ValidationResult` response model.
- Extensible: Easily add/remove rules without modifying the core engine.

### `BusinessRule<T>`
Every rule must implement this contract:

```java
public interface BusinessRule<T> {
    boolean isApplicable(T data);
    ValidationResult validateData(T data);
    String getRuleName();
    int getPriority(); // lower number = higher priority
}
```

### Examples of business rules
#### Patient-Level (EMR)
- DateOFBirthBeforeEnrolmentRule - Ensures patient's date of birth is before enrollment date
- GuardianRequiredForChildRule - Requires guardian details for patients under 18 years
- ARTStartDateRequiredForPositiveRule - Requires ART start date for HIV-positive patients

#### Report-Level (DATIM)
- TxNewConsistencyRule - TX_NEW must match the number of newly initiated ART patients.
- AgeDisaggregationRule - Sum of age bands must equal reported totals
- NoNegativeValuesRule - No negative values allowed in aggregate datasets.

### Next Steps
- Add more rules
- Add unit tests for each rule


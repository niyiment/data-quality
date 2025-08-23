package com.niyiment.dataquality.dto;


public class ValidationResult {
    private final boolean valid;
    private final String errorCode;
    private final String errorMessage;


    private ValidationResult(boolean valid, String errorCode, String errorMessage) {
        this.valid = valid;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static ValidationResult success() {
        return new ValidationResult(true, null, null);
    }

    public static ValidationResult failure(String errorCode, String errorMessage) {
        return new ValidationResult(false, errorCode, errorMessage);
    }

    public boolean isValid() { return valid; }
    public String getErrorCode() { return errorCode; }
    public String getErrorMessage() { return errorMessage; }

}

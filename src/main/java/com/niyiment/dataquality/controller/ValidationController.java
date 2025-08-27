package com.niyiment.dataquality.controller;

import com.niyiment.dataquality.dto.DatimReport;
import com.niyiment.dataquality.dto.PatientDto;
import com.niyiment.dataquality.dto.ValidationResult;
import com.niyiment.dataquality.engine.ValidationEngine;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/api/validation")
@RestController
public class ValidationController {
    private final ValidationEngine<PatientDto> patientValidator;
    private final ValidationEngine<DatimReport> reportValidator;


    public ValidationController(ValidationEngine<PatientDto> patientValidator, ValidationEngine<DatimReport> reportValidator) {
        this.patientValidator = patientValidator;
        this.reportValidator = reportValidator;
    }

    @PostMapping("/patient")
    public List<ValidationResult> validatePatient(@RequestBody PatientDto patient)  {
        return patientValidator.validate(patient);
    }

    @PostMapping("/datim")
    public List<ValidationResult> validateDatimReport(@RequestBody DatimReport report) {
        return reportValidator.validate(report);
    }
}

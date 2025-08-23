package com.niyiment.dataquality.dto;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class PatientDto {
    private UUID id;
    private String medicalRecordNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phone;
    private String address;
    private String nationalId;
    private UUID facilityId;
    private String facilityName;
    private LocalDate enrollmentDate;
    private Instant createdAt;
    private Instant updatedAt;


    public enum Gender {
        MALE, FEMALE, OTHER
    }
}
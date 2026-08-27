package com.netWork.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EducationRequest(
    @NotBlank String institution,

    @NotBlank String degree,

    String fieldOfStudy,

    @NotNull(message = "Start date is required")
    LocalDate startDate,

    LocalDate endDate,

    boolean currentlyStudying
) {
    @AssertTrue(message = "End date must be absent if currently studying; otherwise it must be after the start date")
    boolean isDateRangeValid(){
        if (startDate == null) {
            return true; //@NotNull will provide the appropriate error
        }
        if (currentlyStudying) {
            return endDate == null;
        } else {
            return endDate != null && endDate.isAfter(startDate);
        }
    }
}
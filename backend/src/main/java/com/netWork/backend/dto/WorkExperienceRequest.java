package com.netWork.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WorkExperienceRequest(
    @NotBlank String company,

    @NotBlank String position,

    String description,

    @NotNull(message = "Start date is required")
    LocalDate startDate,

    LocalDate endDate,
    boolean currentlyWorking
) 
{
    @AssertTrue(message = "End date must be absent if currently working; otherwise it must be after the start date")
    boolean isDateRangeValid(){
        if (startDate == null) {
            return true; //@NotNull will provide the appropriate error
        }
        if (currentlyWorking) {
            return endDate == null;
        } else {
            return endDate != null && endDate.isAfter(startDate);
        }
    }
}

package com.sridhar.socialapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Standard error response structure")
public class ErrorResponse {

    @Schema(description = "Time when the error occurred", example = "2025-08-09T15:34:10")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP status code", example = "403")
    private int status;

    @Schema(description = "HTTP status description", example = "Forbidden")
    private String error;

    @Schema(description = "Detailed error message", example = "You do not have permission to access this resource")
    private String message;
}


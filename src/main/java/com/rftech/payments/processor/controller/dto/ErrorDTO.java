package com.rftech.payments.processor.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private Integer statusCode;
    private String message;
    private Timestamp timestamp;
}

package com.rftech.payments.processor.controller.dto;

import lombok.*;

import java.sql.Timestamp;


@AllArgsConstructor
@Data
public class PaymentDTO {
    private Double amount;
    private String currency;
    private String fromAccount;
    private String toAccount;
    private Timestamp timestamp;
}

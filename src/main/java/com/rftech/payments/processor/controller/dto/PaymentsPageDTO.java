package com.rftech.payments.processor.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaymentsPageDTO {
    private List<PaymentDTO> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean isLast;
}

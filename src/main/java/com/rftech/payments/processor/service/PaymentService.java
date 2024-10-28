package com.rftech.payments.processor.service;

import com.rftech.payments.processor.controller.dto.PaymentDTO;
import com.rftech.payments.processor.repository.dao.PaymentDAO;

import java.util.List;

public interface PaymentService {

    List<PaymentDAO> getAllPayments();

    PaymentDTO getPaymentById(Long id);

    PaymentDAO postPayment(PaymentDTO newPayment);

    void deletePayment(Long id);
}

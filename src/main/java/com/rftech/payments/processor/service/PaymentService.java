package com.rftech.payments.processor.service;

import com.rftech.payments.processor.controller.dto.PaymentDTO;
import com.rftech.payments.processor.controller.dto.PaymentsPageDTO;
import com.rftech.payments.processor.repository.dao.PaymentDAO;


import java.util.List;

public interface PaymentService {

    List<PaymentDTO> getAllPayments();

    PaymentsPageDTO getAllPaymentsWithPagination(int pageNumber, int pageSize);

    PaymentDTO getPaymentById(Long id);

    PaymentDAO postPayment(PaymentDTO newPayment);

    void deletePayment(Long id);

}

package com.rftech.payments.processor.service;

import com.rftech.payments.processor.controller.dto.PaymentDTO;
import com.rftech.payments.processor.controller.dto.PaymentsPageDTO;
import com.rftech.payments.processor.repository.dao.PaymentDAO;
import com.rftech.payments.processor.exception.InvalidPaymentIdException;
import com.rftech.payments.processor.exception.PaymentNotFoundException;
import com.rftech.payments.processor.repository.PaymentRepositoryJPA;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @Log4j2
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepositoryJPA paymentRepositoryJPA;

    public List<PaymentDTO> getAllPayments() {
        log.info("PaymentService.getAllPayments()");

        return paymentRepositoryJPA.findAll()
                .stream().map(this::daoToDto)
                .collect(Collectors.toList());
    }

    public PaymentsPageDTO getAllPaymentsWithPagination(int pageNumber, int pageSize) {
        log.info("PaymentService.getAllPaymentsWithPagination(Pageable)");

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<PaymentDAO> payments = paymentRepositoryJPA.findAll(pageable);

        List<PaymentDTO> currentPageContent = payments
                .getContent()
                .stream()
                .map(this::daoToDto)
                .collect(Collectors.toList());

        PaymentsPageDTO results = new PaymentsPageDTO();
        results.setContent(currentPageContent);
        results.setPageNumber(payments.getNumber());
        results.setPageSize(payments.getSize());
        results.setTotalElements(payments.getTotalElements());
        results.setTotalPages(payments.getTotalPages());
        results.setLast(payments.isLast());

        return results;
    }

    public PaymentDTO getPaymentById(Long id) {
        log.info("PaymentService.getPaymentById(Long)");

        validate(id);

        PaymentDAO result = paymentRepositoryJPA.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found by id " + id));

        return daoToDto(result);
    }

    public PaymentDAO postPayment(PaymentDTO newPayment) {
        log.info("PaymentService.postPayment(PaymentDTO)");

        return paymentRepositoryJPA.save(this.dtoToDao(newPayment));
    }

    public void deletePayment(Long id) {
        log.info("PaymentService.deletePayment(Long)");

        paymentRepositoryJPA.deleteById(id);
    }

    // Utils

    private void validate(Long id) throws RuntimeException {
        if(id < 0L) throw new InvalidPaymentIdException("Payment id must be greater than 0!");
    }

    // DAO and DTO Converters

    private PaymentDTO daoToDto(PaymentDAO paymentDAO) {
        return new PaymentDTO(
                paymentDAO.getAmount(),
                paymentDAO.getCurrency(),
                paymentDAO.getFromAccount(),
                paymentDAO.getToAccount(),
                paymentDAO.getTimestamp()
        );
    }

    private PaymentDAO dtoToDao(PaymentDTO newPayment) {
        return new PaymentDAO(
                0L,
                newPayment.getAmount(),
                newPayment.getCurrency(),
                newPayment.getFromAccount(),
                newPayment.getToAccount(),
                newPayment.getTimestamp()
        );
    }

}

package com.rftech.payments.processor.controller;

import com.rftech.payments.processor.controller.dto.PaymentDTO;
import com.rftech.payments.processor.controller.dto.PaymentsPageDTO;
import com.rftech.payments.processor.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/payments")
    public @ResponseBody List<PaymentDTO> getAllPayments() {
        log.info("PaymentController.getAllPayments()");

        return paymentService.getAllPayments();
    }

    @GetMapping("/payments-pagination")
    public @ResponseBody ResponseEntity<PaymentsPageDTO> getAllPaymentsWithPagination(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        log.info("PaymentController.getAllPaymentsWithPagination({},{})", pageNumber, pageSize);

        PaymentsPageDTO results = paymentService.getAllPaymentsWithPagination(pageNumber, pageSize);

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/payments/{id}")
    public @ResponseBody ResponseEntity<PaymentDTO> getPaymentsById(@PathVariable Long id){
        log.info("PaymentController.getPaymentById({})", id);

        PaymentDTO payment = paymentService.getPaymentById(id);

        return new ResponseEntity<>(
                payment
                , HttpStatus.OK);
    }

    @PostMapping("/payments")
    public @ResponseBody ResponseEntity<Long> postPayments(@RequestBody PaymentDTO newPayment){
        log.info("PaymentController.postPayment({})", newPayment);

       Long newPaymentId = paymentService.postPayment(newPayment).getId();

        return new ResponseEntity<>(
                newPaymentId
                , HttpStatus.OK);
    }

    @DeleteMapping("/payments/{id}")
    public void deletePayments(@PathVariable Long id){
        log.info("PaymentController.deletePayment(id)");

        paymentService.deletePayment(id);
    }
}

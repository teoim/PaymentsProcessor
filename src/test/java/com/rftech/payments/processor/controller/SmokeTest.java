package com.rftech.payments.processor.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    PaymentController paymentController;

    @Test
    void contextLoads() {
        assertThat(paymentController).isNotNull();
    }
}

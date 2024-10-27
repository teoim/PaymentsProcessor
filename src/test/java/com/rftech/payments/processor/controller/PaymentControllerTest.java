package com.rftech.payments.processor.controller;

import com.rftech.payments.processor.controller.dto.PaymentDTO;
import com.rftech.payments.processor.controller.dto.ErrorDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentControllerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;



    @Test
    @Order(1)
    void testGetPaymentsShouldFindThree(){
        List<PaymentDTO> results = this.testRestTemplate
                .getForObject("http://localhost:" + port + "/payments", List.class);

        assertThat(results)
                .hasSize(3)
                .doesNotContainNull();
    }

    @Test
    @Order(2)
    void testPostPayments() {
        PaymentDTO newPayment = new PaymentDTO(100.10
                , "EUR"
                , "600100"
                , "600101"
                , Timestamp.valueOf(LocalDateTime.now()));
        HttpEntity<PaymentDTO> request = new HttpEntity<>(newPayment);

        // 1 Post payment
        ResponseEntity<Long> newPaymentIdResponse = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/payments"
                , request
                , Long.class);

        assertThat(newPaymentIdResponse.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(newPaymentIdResponse.getBody())
                .isNotNull()
                .isEqualTo(4);

        // 2 Verify payment in database
        ResponseEntity<PaymentDTO> newPaymentInDatabase =
                testRestTemplate.getForEntity(
                        "http://localhost:" + port + "/payments/" + newPaymentIdResponse.getBody()
                        , PaymentDTO.class);

        assertThat( newPaymentInDatabase.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat( newPaymentInDatabase.getBody()).isEqualTo(newPayment);  // TODO: fails because the nanos piece of timestamp loses precision in the DB
    }

    @Test
    @Order(3)
    void testDeletePayments(){
        long paymentIdToDelete = 2L;

        // 1 Delete payment by id
        testRestTemplate.delete("http://localhost:" + port + "/payments/" + paymentIdToDelete);

        // 2 Verify the payment is no longer in the database
        ResponseEntity<ErrorDTO> response = this.testRestTemplate.getForEntity(
                "http://localhost:" + port + "/payments/" + paymentIdToDelete,
                ErrorDTO.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @Order(4)
    void testGetPaymentByInvalidId(){
        long invalidPaymentId = -333L;

        ResponseEntity<ErrorDTO> response = this.testRestTemplate.getForEntity(
                "http://localhost:" + port + "/payments/" + invalidPaymentId,
                ErrorDTO.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
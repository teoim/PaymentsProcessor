package com.rftech.payments.processor.controller.ut;

import com.rftech.payments.processor.repository.PaymentRepositoryJPA;
import com.rftech.payments.processor.repository.dao.PaymentDAO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PaymentRepositoryTest {
    @Autowired
    private PaymentRepositoryJPA paymentRepositoryJPA;

    @Test
    public void TestSavePayment(){
        PaymentDAO newPayment = new PaymentDAO(
                0L
                , 55.00
                , "USD"
                , "700100"
                , "700200"
                , Timestamp.valueOf(LocalDateTime.now()));

        PaymentDAO savedPayment = paymentRepositoryJPA.save(newPayment);

        Assertions.assertThat(savedPayment).isNotNull();
        Assertions.assertThat(savedPayment.getId()).isGreaterThan(0L);
        Assertions.assertThat(savedPayment.getAmount()).isEqualTo(55.00);
    }
}

package com.rftech.payments.processor.repository.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity(name = "payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDAO {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String currency;
    private String fromAccount;
    private String toAccount;
    private Timestamp timestamp;

}

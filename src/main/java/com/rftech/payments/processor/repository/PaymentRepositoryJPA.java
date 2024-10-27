package com.rftech.payments.processor.repository;


import com.rftech.payments.processor.repository.dao.PaymentDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepositoryJPA extends JpaRepository<PaymentDAO, Long> {

}

package com.tbank.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tbank.test.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllBySourceAccountId(long id);
}

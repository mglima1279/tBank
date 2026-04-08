package com.tbank.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tbank.test.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

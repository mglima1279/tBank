package com.tbank.test.entities;

import java.time.LocalDateTime;

import com.tbank.test.enums.PayMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long value;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PayMethod payMethod;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @ManyToOne
    @JoinColumn(name = "source_account_id", nullable = true)
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "destiny_account_id", nullable = true)
    private Account destinyAccount;
}

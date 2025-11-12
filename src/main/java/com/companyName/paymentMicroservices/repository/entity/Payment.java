package com.companyName.paymentMicroservices.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="payments")
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    @Getter
    @Setter
    private Long id;

    @Column(name="TRANSACTION_DATE")
    @Getter @Setter
    private LocalDate transaction_date; //YYYY-mm-dd 2025-09-30

    @Column(name="TRANSACTION_DESCRIPTION")
    @Getter @Setter
    private String transaction_description;

    @Column(name="FK_USER")
    @Getter @Setter
    private String fkUser;

    @Column(name="AMOUNT")
    @Getter @Setter
    private BigDecimal amount;

    @Column(name="CURRENCY")
    @Getter @Setter
    private String currency;

    public Payment(Payment account) { //serve a fare new Payment(account), altrimenti devi fare new Payment(account.getId,account.getName,....)
        this.id = account.getId();
        this.transaction_date = account.getTransaction_date();
        this.transaction_description = account.getTransaction_description();
        this.fkUser = account.getFkUser();
        this.amount = account.getAmount();
        this.currency = account.getCurrency();
    }
}
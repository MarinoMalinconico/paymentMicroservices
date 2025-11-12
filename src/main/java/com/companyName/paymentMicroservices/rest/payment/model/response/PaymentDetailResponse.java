package com.companyName.paymentMicroservices.rest.payment.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetailResponse {

    @Getter
    @Setter
    private String id;

    @Getter @Setter
    private LocalDate transaction_date;

    @Getter @Setter
    private String transaction_description;

    @Getter @Setter
    private String fkUser;

    @Getter @Setter
    private BigDecimal amount;

    @Getter @Setter
    private String currency;
}

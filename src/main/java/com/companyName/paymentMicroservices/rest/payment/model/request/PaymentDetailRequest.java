package com.companyName.paymentMicroservices.rest.payment.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetailRequest {

    @Getter
    @Setter
    private String cf;

}

package com.companyName.paymentMicroservices.rest.payment.delegate;


import com.companyName.coreMicroservices.repository.entity.Payment;
import com.companyName.paymentMicroservices.rest.payment.model.response.PaymentDetailResponse;

import java.security.InvalidParameterException;
import java.util.List;

public interface PaymentDetailDelegate {

    List<PaymentDetailResponse> getPaymentDetailQuery(String FkUser) throws InvalidParameterException ;
    List<PaymentDetailResponse> getPaymentDetail(String FkUser) throws InvalidParameterException ;
    List<PaymentDetailResponse> getAllPaymentList() throws InvalidParameterException ;
    List<PaymentDetailResponse> updatePaymentDetail(Payment payment) throws InvalidParameterException ;
    boolean deletePaymentDetail(Payment payment) throws InvalidParameterException ;
    boolean deletePaymentDetailByCf(Payment payment) throws InvalidParameterException ;
}

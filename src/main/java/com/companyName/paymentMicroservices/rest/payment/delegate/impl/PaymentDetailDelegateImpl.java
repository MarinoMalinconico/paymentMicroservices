package com.companyName.paymentMicroservices.rest.payment.delegate.impl;

import com.companyName.coreMicroservices.repository.PaymentRepository;
import com.companyName.coreMicroservices.repository.entity.Payment;
import com.companyName.paymentMicroservices.rest.payment.delegate.PaymentDetailDelegate;
import com.companyName.paymentMicroservices.rest.payment.model.response.PaymentDetailResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PaymentDetailDelegateImpl implements PaymentDetailDelegate {

    @Autowired
    PaymentRepository repository;

    @Override
    public List<PaymentDetailResponse> getPaymentDetailQuery(String FkUser) {
        log.debug("Into getPaymentDetail delegate with PathParameter [{}]", FkUser);

        List<Payment> dbResult = repository.getAllPaymentPerUser(FkUser);
        List<PaymentDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    private List<PaymentDetailResponse> dbResultToDto(List<Payment> dtos) {
        List<PaymentDetailResponse> formattedDTOs = new ArrayList<>();
        for (Payment dto : dtos) {
            PaymentDetailResponse fileDto = new PaymentDetailResponse();
            fileDto.setId(dto.getId());
            fileDto.setTransaction_date(dto.getTransaction_date());
            fileDto.setTransaction_description(dto.getTransaction_description());
            fileDto.setCurrency(dto.getCurrency());
            fileDto.setFkUser(dto.getFkUser());
            fileDto.setAmount(dto.getAmount() != null ? dto.getAmount().setScale(2,BigDecimal.ROUND_HALF_DOWN) : null);
            formattedDTOs.add(fileDto);
        }
        return formattedDTOs;
    }

    @Override
    public List<PaymentDetailResponse> getPaymentDetail(String FkUser) {
        log.debug("Into getPaymentDetail delegate with PathParameter [{}]", FkUser);

        List<Payment> dbResult = repository.findByfkUser(FkUser);
        List<PaymentDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    @Override
    public List<PaymentDetailResponse> getAllPaymentList() {
        log.debug("Into getPaymentDetail delegate with all");

        List<Payment> dbResult = repository.findAll();
        List<PaymentDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }


    @Override
    @Transactional
    public List<PaymentDetailResponse> updatePaymentDetail(Payment newPayment) {
        log.debug("Into updatePaymentDetail");

        Optional<Payment> currentPayment = repository.findById(newPayment.getId());
        currentPayment.ifPresent(payment -> {
            payment.setTransaction_date(newPayment.getTransaction_date() != null ? newPayment.getTransaction_date() : currentPayment.get().getTransaction_date());
            payment.setTransaction_description(newPayment.getTransaction_description() != null ? newPayment.getTransaction_description() : currentPayment.get().getTransaction_description());
            payment.setFkUser(newPayment.getFkUser() != null ? newPayment.getFkUser() : currentPayment.get().getFkUser());
            payment.setAmount(newPayment.getAmount() != null ? newPayment.getAmount() : currentPayment.get().getAmount());
            payment.setCurrency(newPayment.getCurrency() != null ? newPayment.getCurrency() : currentPayment.get().getCurrency());
        });

        Optional<Payment> dbResult = repository.findById(currentPayment.get().getId());
        List<PaymentDetailResponse> response = dbResultToDto(dbResult.stream().toList());

        return response;
    }

    @Override
    public boolean deletePaymentDetail(Payment payment) {
        log.debug("Into deletePaymentDetail for [{} - {}]",payment.getFkUser(),payment.getId());

        repository.delete(new Payment(payment));

        return true;
    }

    @Override
    public boolean deletePaymentDetailByCf(Payment payment) {
        log.debug("Into deletePaymentDetail for [{} - {}]",payment.getFkUser(),payment.getId());

        List<Payment> dbResult = repository.findByfkUser(payment.getFkUser());

        try {
            for(Payment payToDelete:dbResult){
                repository.deleteById(payToDelete.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //repository.deletepaymentByfkUser(payment.getFkUser());

        return true;
    }
}

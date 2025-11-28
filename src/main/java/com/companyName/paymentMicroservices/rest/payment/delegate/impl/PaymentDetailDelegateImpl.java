package com.companyName.paymentMicroservices.rest.payment.delegate.impl;

import com.companyName.coreMicroservices.repository.PaymentRepository;
import com.companyName.coreMicroservices.repository.entity.Payment;
import com.companyName.paymentMicroservices.rest.payment.delegate.PaymentDetailDelegate;
import com.companyName.paymentMicroservices.rest.payment.model.response.PaymentDetailResponse;
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
    public List<PaymentDetailResponse> getPaymentDetail(String FkUser) {
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
            fileDto.setAmount(dto.getAmount().setScale(2,BigDecimal.ROUND_HALF_DOWN));
            formattedDTOs.add(fileDto);
        }
        return formattedDTOs;
    }

    @Override
    public List<PaymentDetailResponse> getPaymentDetailJPA(String FkUser) {
        log.debug("Into getPaymentDetail delegate with PathParameter [{}]", FkUser);

        List<Payment> dbResult = repository.findByfkUser(FkUser);
        List<PaymentDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    @Override
    public List<PaymentDetailResponse> getAllJPA() {
        log.debug("Into getPaymentDetail delegate with all");

        List<Payment> dbResult = repository.findAll();
        List<PaymentDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    //togliere, pagamento va aggiunto nell'invoice e collegato
    @Override
    public List<PaymentDetailResponse> addPaymentDetail(Payment payment) {
        log.debug("Into addPaymentDetail");

        repository.save(new Payment(payment.getId(),payment.getTransaction_date(), payment.getTransaction_description(), payment.getFkUser(), payment.getAmount(), payment.getCurrency()));

        List<Payment> dbResult = repository.findByfkUser(payment.getFkUser());
        List<PaymentDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    @Override
    public List<PaymentDetailResponse> updatePaymentDetail(Payment payment) {
        log.debug("Into updatePaymentDetail");

        repository.save(new Payment(payment));

        Optional<Payment> currentPayment = repository.findById(payment.getId().toString());
        //vecchio metodo
        //currentPayment.get().setId(payment.getId());
        //.
        //.
        //.
        currentPayment.get().updatePayment(payment);
        repository.save(currentPayment.get());

        List<Payment> dbResult = repository.findByfkUser(payment.getFkUser());
        List<PaymentDetailResponse> response = dbResultToDto(dbResult);

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

        repository.deletepaymentByfkUser(payment.getFkUser());

        return true;
    }
}

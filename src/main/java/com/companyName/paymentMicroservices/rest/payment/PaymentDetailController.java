package com.companyName.paymentMicroservices.rest.payment;

import com.companyName.paymentMicroservices.common.model.BasicResponse;
import com.companyName.coreMicroservices.repository.entity.Payment;
import com.companyName.paymentMicroservices.rest.payment.delegate.PaymentDetailDelegate;
import com.companyName.paymentMicroservices.rest.payment.exceptions.PaymentDetailException;
import com.companyName.paymentMicroservices.rest.payment.model.request.PaymentDetailRequest;
import com.companyName.paymentMicroservices.rest.payment.model.response.PaymentDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.List;

@Slf4j
@Controller
public class PaymentDetailController {

    @Autowired
    PaymentDetailDelegate delegate;

    @RequestMapping(value = "/paymentDetailBasicResponse",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<PaymentDetailResponse>>> paymentDetailBasicResponse(@RequestBody PaymentDetailRequest payment) throws InvalidParameterException, PaymentDetailException {

        log.info("Entering in paymentDetail service - PathVariable: [{}]", payment.getCf());

        List<PaymentDetailResponse> delegateResult =  null;
        BasicResponse<List<PaymentDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getPaymentDetail(payment.getCf());
            if (!delegateResult.isEmpty() && delegateResult!=null){
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
                throw new PaymentDetailException("No data found for request param: "+payment.getCf());
            }
            log.debug("result delegate.getPaymentDetail(payment) [{}]", response);
        } catch (InvalidParameterException  e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);

        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    //con path parameter
    @RequestMapping(value = "/paymentDetailBasicResponseParam",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<PaymentDetailResponse>>> paymentDetailBasicResponse(@RequestParam String FkUser) throws InvalidParameterException {

        log.info("Entering in paymentDetail service(param) - PathVariable: [{}]", FkUser);

        List<PaymentDetailResponse> delegateResult =  null;
        BasicResponse<List<PaymentDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getPaymentDetail(FkUser);
            if (!delegateResult.isEmpty() && delegateResult!=null){
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getPaymentDetail(payment) [{}]", response);
        } catch (InvalidParameterException  e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);

        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    //get con read e jpa
    @RequestMapping(value = "/paymentDetailBasicResponseParam",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<PaymentDetailResponse>>> paymentDetailBasicResponseGET(@RequestParam String FkUser) throws InvalidParameterException {

        log.info("Entering in paymentDetail service(param)(JPA) - PathVariable: [{}]", FkUser);

        List<PaymentDetailResponse> delegateResult =  null;
        BasicResponse<List<PaymentDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getPaymentDetailJPA(FkUser);
            if (!delegateResult.isEmpty() && delegateResult!=null){
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getPaymentDetail(payment) [{}]", response);
        } catch (InvalidParameterException  e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);

        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    //get all jpa
    @RequestMapping(value = "/paymentDetailBasicResponseParamAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<PaymentDetailResponse>>> paymentDetailBasicResponseGET() throws InvalidParameterException {

        log.info("Entering in paymentDetail service(param)(JPA)(all)");

        List<PaymentDetailResponse> delegateResult =  null;
        BasicResponse<List<PaymentDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getAllJPA();
            if (!delegateResult.isEmpty() && delegateResult!=null){
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getPaymentDetail(payment) [{}]", response);
        } catch (InvalidParameterException  e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);

        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @RequestMapping(value = "/AddPayment",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<PaymentDetailResponse>>> addPayment(@RequestBody Payment payment) throws InvalidParameterException {

        log.info("Entering in add payment of [{}]", payment.getFkUser());

        List<PaymentDetailResponse> delegateResult =  null;
        BasicResponse<List<PaymentDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.addPaymentDetail(payment);
            if (!delegateResult.isEmpty() && delegateResult!=null){
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getPaymentDetail(payment) [{}]", response);
        } catch (InvalidParameterException  e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);

        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @RequestMapping(value = "/UpdatePayment",
    method = RequestMethod.PUT,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<PaymentDetailResponse>>> updatePayment(@RequestBody Payment payment) throws InvalidParameterException {

        log.info("Entering in payment update of [{}]",payment.getFkUser());

        List<PaymentDetailResponse> delegateResult = null;
        BasicResponse<List<PaymentDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult = delegate.updatePaymentDetail(payment);
            if (!delegateResult.isEmpty() && delegateResult != null) {
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getPaymentDetail(payment) [{}]", response);
        } catch (InvalidParameterException e) {
            log.error("ERROR {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {}",e.getMessage(), e);
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @RequestMapping(value = "/DeletePayment",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<PaymentDetailResponse>>> deletePayment(@RequestBody Payment payment) throws InvalidParameterException {

        log.info("Entering in payment delete of [{}]",payment.getFkUser());

        boolean deleted=false;

        List<PaymentDetailResponse> delegateResult = null;
        BasicResponse<List<PaymentDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getPaymentDetailJPA(payment.getFkUser());
            deleted=delegate.deletePaymentDetail(payment);
            if (!delegateResult.isEmpty() && delegateResult != null) {
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getPaymentDetail(payment) [{}]", response);
        } catch (InvalidParameterException e) {
            log.error("ERROR {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {}",e.getMessage(), e);
        }
        log.info(deleted ? "eseguita delete di {} - {}" : "errore nella delete di {}", payment.getFkUser(),payment.getId());

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @RequestMapping(value = "/DeletePaymentByCf",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<PaymentDetailResponse>>> deletePaymentByCf(@RequestBody Payment payment) throws InvalidParameterException {

        log.info("Entering in payment delete of [{}]",payment.getFkUser());

        boolean deleted=false;

        List<PaymentDetailResponse> delegateResult = null;
        BasicResponse<List<PaymentDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getPaymentDetailJPA(payment.getFkUser());
            deleted=delegate.deletePaymentDetailByCf(payment);
            if (!delegateResult.isEmpty() && delegateResult != null) {
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getPaymentDetail(payment) [{}]", response);
        } catch (InvalidParameterException e) {
            log.error("ERROR {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {}",e.getMessage(), e);
        }
        log.info(deleted ? "eseguita delete di {} - {}" : "errore nella delete di {}", payment.getFkUser(),payment.getId());

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
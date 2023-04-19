package com.gc.hrpayroll.resources;

import com.gc.hrpayroll.entities.Payment;
import com.gc.hrpayroll.services.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {
    private final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final Map<Long, ResponseEntity<Payment>> CACHE = new HashMap<>();
    private PaymentService paymentService;

    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(value = "/{workerId}/days/{days}")
    @CircuitBreaker(name = "hr-payrollCB", fallbackMethod = "getPaymentInCache")
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer days){
        try {
            Payment payment = paymentService.getPayment(workerId, days);
            ResponseEntity response = ResponseEntity.ok().body(payment);
            logger.info("Save in cache");
            CACHE.put(workerId, response);
            return response;
        }catch (Exception e){
            logger.error("Not found");
            throw e;
        }
    }
    private ResponseEntity<Payment> getPaymentInCache(Long workerId,Integer days,  Exception e ){
        logger.info("Searching in Cache");
        return CACHE.getOrDefault(workerId, new ResponseEntity<Payment>(HttpStatus.NOT_FOUND));
    }
}

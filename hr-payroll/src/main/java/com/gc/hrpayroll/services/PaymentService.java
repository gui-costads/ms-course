package com.gc.hrpayroll.services;

import com.gc.hrpayroll.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public Payment getPayment(Long workerId, Integer days){
        return new Payment("mario", 200.0, days);
    }
}

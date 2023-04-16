package com.gc.hrpayroll.services;

import com.gc.hrpayroll.entities.Payment;
import com.gc.hrpayroll.entities.Worker;
import com.gc.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    private WorkerFeignClient workerFeignClient;

    public PaymentService(WorkerFeignClient workerFeignClient) {
        this.workerFeignClient = workerFeignClient;
    }


    public Payment getPayment(Long workerId, Integer days){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", ""+workerId);


        Worker worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}

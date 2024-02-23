package com.tronicmart.tronicmart.service;

import com.tronicmart.tronicmart.payload.PaymentDetails;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    public PaymentDetails CreateOrder(Double amount);
}

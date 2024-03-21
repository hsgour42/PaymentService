package com.example.paymentservice.paymentgateway;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {   //using adaptor design patter here

    String generatePaymentLink(String orderId, Long amount,String phoneNumber , String name, String email) throws RazorpayException, StripeException;
}

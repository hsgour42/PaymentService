package com.example.paymentservice.controllers;


import com.stripe.model.Event;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripeWebhook")
public class StripeWebhookController {

    @PostMapping
    public void stripeWebhook(@RequestBody Event event){
    System.out.println("stripe webhook");
        if(event.getType().equals("payment_link.created") ){
            System.out.println("payment_link.created");
        } else if (event.getType().equals("payment_link.updated")) {
            System.out.println("payment_link.updated");
        }
    }
}

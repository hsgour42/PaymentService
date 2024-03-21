package com.example.paymentservice.paymentgateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripePaymentGateway implements PaymentGateway{
    @Value("${stripe.key.secret}")
    private String stripeApiKey;
    @Override
    public String generatePaymentLink(String orderId, Long amount,String phoneNumber , String name, String email) throws StripeException {

        Stripe.apiKey = stripeApiKey;

//        PaymentLinkCreateParams params =
//                PaymentLinkCreateParams.builder()
//                        .addLineItem(
//                                PaymentLinkCreateParams.LineItem.builder()
//                                        .setPrice("price_1MoC3TLkdIwHu7ixcIbKelAC")
//                                        .setQuantity(1L)
//                                        .build()
//                        )
//                        .build();
//
//        PaymentLink paymentLink = PaymentLink.create(params);

        Map<String , Object> priceParam = new HashMap<>();
        priceParam.put("unit_amount"  , amount);
        priceParam.put("currency" , "INR");

        Map<String , Object> productData = new HashMap<>();
        productData.put("name" , "Iphone");

        priceParam.put("product_data" , productData);

        Price price = Price.create(priceParam);

        Map<String , Object> lineItem = new HashMap<>();
        lineItem.put("price" , price.getId());
        lineItem.put("quantity" , 2);


        List<Object> lineItems = new ArrayList<>();
        lineItems.add(lineItem);



       Map<String  , Object > redirect = new HashMap<>();
        redirect.put("url" , "https://www.scaler.com/");

        Map<String , Object> after_completion = new HashMap<>();
        after_completion.put("type", "redirect");
        after_completion.put("redirect" , redirect);



        Map<String , Object> params = new HashMap<>();
        params.put("line_items", lineItems);
        params.put("after_completion" ,after_completion);

        PaymentLink paymentLink = PaymentLink.create(params);



        return paymentLink.getUrl();



//        return paymentLink.toString();
    }
}

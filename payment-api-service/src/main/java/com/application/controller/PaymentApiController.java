package com.application.controller;

import com.Bean.ProfilBean;
import com.Model.OrderModel;
import com.Proxy.ProfilProxy;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@ResponseBody
public class PaymentApiController {

    @Autowired
    private PaypalProxy paypalProxy;

    @Autowired
    private ProfilProxy profilProxy;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String home(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    @RequestMapping(value="/pay", method = RequestMethod.POST)
    //@ModelAttribute("order")
    public HashMap<String, String> payment(@RequestBody OrderModel order, HttpServletRequest request) {
        HashMap<String, String> Data=new HashMap<>();
        try {
            Payment payment = paypalProxy.createPayment(
                    order.getTotal(),
                    order.getCurrency(),
                    order.getMethod(),
                    order.getIntent(),
                    order.getDescription(),
                    request.getRequestURL().toString() + CANCEL_URL,
                    request.getRequestURL().toString() + SUCCESS_URL
            );
            for(Links link:payment.getLinks())
                if(link.getRel().equals("approval_url"))
                    Data.put("approval_url",link.getHref());
        } catch (PayPalRESTException e) {
            Data.put("message",e.getMessage());
        }
        return Data;
    }

    @GetMapping(value = CANCEL_URL)
    public HashMap<String, String> cancelPay() {
        HashMap<String, String> Data=new HashMap<>();
        Data.put("status","canceled");
        Data.put("message","The payment operation is canceled");
        return Data;
    }

    @GetMapping(value = SUCCESS_URL)
    public HashMap<String, String> successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        HashMap<String, String> Data=new HashMap<>();
        Data.put("paymentId",paymentId);
        Data.put("PayerID",payerId);
        try {
            Payment payment = paypalProxy.executePayment(paymentId, payerId);
            Data.put("details",payment.toJSON());
            if (payment.getState().equals("approved"))
                Data.put("status","succeeded");
            else
                Data.put("status","unapproved");
            Data.put("message","The payment operation is succeeded");
        } catch (PayPalRESTException e) {
            Data.put("status","uncompleted");
            Data.put("message","The payment operation is succeeded, with unexpected error in application");
            Data.put("detail",e.getMessage());
        }
        ProfilBean profil=profilProxy.getProfil(1);
        Data.put("fullName",profil.getFirstName()+" "+profil.getLastName());
        Data.put("email", profil.getEmail());
        return Data;
    }

}

package com.poc.kafka.service;


import com.poc.kafka.model.request.PaymentRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaymentServiceTests {

    @Autowired
    private PaymentService paymentService;

    private List<PaymentRequest> requests;

    @BeforeAll
    public void populateRequestModels() {
        String walletId = "195-62G-97D";
        this.requests = List.of(
                new PaymentRequest(1, "Belkin 90WL", "Headphone", walletId),
                new PaymentRequest(2, "Huawei FreeBuds Pro", "TWS Headphone", walletId),
                new PaymentRequest(3, "Keychron K3 Lowprofile", "Keyboard", walletId),
                new PaymentRequest(4, "Logitech WL-1966", "Mouse", walletId),
                new PaymentRequest(5, "HP 24M100F", "Monitor", walletId),
                new PaymentRequest(6, "Asus B460", "Motherboard", walletId),
                new PaymentRequest(7, "Ryzen 3200G", "Processor", walletId)
        );
    }

    @Test
    @Disabled
    public void paymentTest() throws InterruptedException {
        List<String> paymentIds = this.requests.stream()
                .map(request -> paymentService.pay(request))
                .collect(Collectors.toList());

        Thread.sleep(1000);
        Assertions.assertEquals(paymentIds.size(), requests.size());
    }
}

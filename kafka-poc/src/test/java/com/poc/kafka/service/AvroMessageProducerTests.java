package com.poc.kafka.service;

import com.poc.kafka.model.avro.Payment;
import com.poc.kafka.producer.AvroMessageProducer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AvroMessageProducerTests {

    @Autowired
    private AvroMessageProducer avroProducer;

    private List<Payment> requests;

    @BeforeAll
    public void populateRequestModels() {
        String walletId = "195-62G-97D";
        this.requests = List.of(
                Payment.newBuilder().setProductId(1L).setProductName("Belkin 90WL").setCategory("Headphone").setWalletId(walletId).build(),
                Payment.newBuilder().setProductId(2L).setProductName("Huawei FreeBuds Pro").setCategory("TWS Headphone").setWalletId(walletId).build(),
                Payment.newBuilder().setProductId(3L).setProductName("Keychron K3 Lowprofile").setCategory("Keyboard").setWalletId(walletId).build(),
                Payment.newBuilder().setProductId(4L).setProductName("Logitech WL-1966").setCategory("Mouse").setWalletId(walletId).build(),
                Payment.newBuilder().setProductId(5L).setProductName("HP 24M100F").setCategory("Monitor").setWalletId(walletId).build(),
                Payment.newBuilder().setProductId(6L).setProductName("Asus B460").setCategory("Motherboard").setWalletId(walletId).build(),
                Payment.newBuilder().setProductId(7L).setProductName("Ryzen 3200G").setCategory("Processor").setWalletId(walletId).build()
        );
    }

    @Test
    @Disabled
    public void testAvroPublisher() throws InterruptedException {
/*        Payment payment = Payment.newBuilder()
                .setProductId(1L)
                .setProductName("Football")
                .setCategory("Khela-Dhula")
                .setWalletId("ABC-123-XYZ")
                .build();

        String key = avroProducer.produce(payment);
        Assertions.assertNotNull(key);*/

        List<String> paymentIds = this.requests.stream()
                .map(request -> avroProducer.produce(request))
                .collect(Collectors.toList());

        Thread.sleep(1000);
        Assertions.assertEquals(paymentIds.size(), requests.size());

    }
}

package io.solo.kafkaproducer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Random;

@RestController
public class MessageController {

    final KafkaTemplate<String, String> template;

    public MessageController(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    @GetMapping("/")
    public String index() throws InterruptedException {
        Thread.sleep(200 + new Random().nextInt(2000));

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("mytopic",  "test" + 20 + new Random().nextInt(200));

        producerRecord.headers().add("client-id", "2334".getBytes(StandardCharsets.UTF_8));
        producerRecord.headers().add("data-file", "incoming-data.txt".getBytes(StandardCharsets.UTF_8));

        template.send(producerRecord);
        return "Ok";
    }

}
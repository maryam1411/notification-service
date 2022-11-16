package com.notification.service.listener.emaillistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class EmailTopicListener {
    private static final Logger LOG = LoggerFactory.getLogger(EmailTopicListener.class);

    @KafkaListener(topics = "CREDIT_TRANSACTION", groupId = "TRANSACTION")
    public void consume(@Payload String email, @Header(KafkaHeaders.MESSAGE_KEY) Object messageKey) {
        LOG.info("Received data='{}'", email);
        System.out.println("Transaction Successful notification sent via email");
        System.out.println("Message received " + email + "  " + messageKey.toString());


    }


}

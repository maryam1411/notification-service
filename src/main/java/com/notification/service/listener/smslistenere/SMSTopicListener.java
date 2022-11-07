package com.notification.service.listener.smslistenere;

import com.notification.service.listener.emaillistener.EmailTopicListener;
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
public class SMSTopicListener {
    private static final Logger LOG = LoggerFactory.getLogger(EmailTopicListener.class);


    @KafkaListener(topics = "ACCOUNT_CREATED", groupId = "ACCOUNT")
    public void listenAccountSMS(@Payload String phoneNumber, @Header(KafkaHeaders.MESSAGE_KEY) Object messageKey) {
        LOG.info("Received data='{}'", phoneNumber);
        System.out.println("Account created notification sent via sms");
        System.out.println("Message received " + phoneNumber + "  " + messageKey.toString());

    }
}




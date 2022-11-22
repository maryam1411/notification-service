package com.notification.service.listener.smslistenere;

import com.notification.service.domain.Account;
import com.notification.service.listener.emaillistener.EmailTopicListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Properties;

@Service
@EnableKafka
public class SMSTopicListener {
    private static final Logger LOG = LoggerFactory.getLogger(EmailTopicListener.class);


    @KafkaListener(topics = "ACCOUNT_CREATED", groupId = "ACCOUNT")
    public void listenAccountSMS() {
        //    @Header(KafkaHeaders.MESSAGE_KEY) Object messageKey
//    @Payload Account account
//        LOG.info("Received data='{}'", account);
//        System.out.println("Account created notification sent via sms");
//        System.out.println("Message received " + account + "  " + messageKey.toString());
        Properties props = new Properties();
        props.put("key-deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value-deserializer", "com.notification.service.deserializer.AccountCreationTopicDeserializer");
        props.put("bootstrap-servers", "localhost:9092");
        try (KafkaConsumer<String, Account> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList("ACCOUNT_CREATED"));
            while (true) {
                ConsumerRecords<String, Account> messages = consumer.poll(100);
                for (ConsumerRecord<String, Account> message : messages) {
                    System.out.println("Message received " + message.value().toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}




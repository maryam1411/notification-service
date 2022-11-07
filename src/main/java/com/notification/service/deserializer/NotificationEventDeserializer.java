package com.notification.service.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.service.domain.NotificationEvent;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class NotificationEventDeserializer implements Deserializer {

    @Override
    public void configure(Map configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public NotificationEvent deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        NotificationEvent event = null;
        try {
            event = mapper.readValue(bytes, NotificationEvent.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return event;
    }


    @Override
    public Object deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}

package com.sq.demo.kafka;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sq.demo.model.EmployeeEntity;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomerEventProducer {

    private final KafkaTemplate<Long, String> kafkaTemplate;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public CustomerEventProducer(KafkaTemplate kafkaTemplate) {
        Map<String, Object> senderProps = senderProps();
        ProducerFactory<Long, String> pf =
                  new DefaultKafkaProducerFactory<Long, String>(senderProps);
        KafkaTemplate<Long, String> template = new KafkaTemplate<>(pf);
        this.kafkaTemplate = template;
        
    }

    public void processMessage(EmployeeEntity employee) throws JsonProcessingException {

        String payLoad = mapper.writeValueAsString(employee);

        ListenableFuture<SendResult<Long, String>> furture = kafkaTemplate.send("customer_event", Instant.now().getEpochSecond(), payLoad);
        
        try {
            log.debug("response {}", furture.get());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Exception {} ", e.getMessage() );
        }
        
    }
    
    private Map<String, Object> senderProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.ACKS_CONFIG, "0");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

}

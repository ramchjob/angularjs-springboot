
  package com.sq.demo.kafka;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.kafka.core.KafkaTemplate; import
  org.springframework.stereotype.Component;
  
  import com.fasterxml.jackson.core.JsonProcessingException; import
  com.fasterxml.jackson.databind.ObjectMapper; import
  com.sq.demo.model.EmployeeEntity;
  
  @Component public class CustomerEventProducer {
  
  private final KafkaTemplate kafkaTemplate;
  
  ObjectMapper mapper = new ObjectMapper();
  
  @Autowired public CustomerEventProducer(KafkaTemplate kafkaTemplate) {
  this.kafkaTemplate = kafkaTemplate; }
  
  public void processMessage(EmployeeEntity employee) throws
  JsonProcessingException {
  
  String payLoad = mapper.writeValueAsString(employee);
  
  kafkaTemplate.send("customer_event", payLoad);
  
  }
  
  }
 
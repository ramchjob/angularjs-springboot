package com.ram.learn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ram.learn.exception.RecordNotFoundException;
import com.ram.learn.kafka.CustomerEventProducer;
import com.ram.learn.model.EmployeeEntity;
import com.ram.learn.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

 
@Service
@Slf4j
public class EmployeeService {
     
    @Autowired
    EmployeeRepository repository;
    
    @Autowired
    CustomerEventProducer producer;
     
    public List<EmployeeEntity> getAllEmployees()
    {
        List<EmployeeEntity> employeeList = repository.findAll();
        
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<EmployeeEntity>();
        }
    }
    
    public List<EmployeeEntity> getEmployeesByName(String name){
        return repository.getEmployeeswithByName(name);
    }
    
    public List<EmployeeEntity> getEmployeesByFirstName(String name){
        return repository.getEmployeesByFirstName(name);
    }
     
    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
     
    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws RecordNotFoundException, JsonProcessingException
    {
        Optional<EmployeeEntity> employee = Optional.empty();
        if (null != entity.getId()) {
            employee = repository.findById(entity.getId());
        }
         
        if(employee.isPresent())
        {
            EmployeeEntity newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
 
            newEntity = repository.save(newEntity);
             
            producer.processMessage(newEntity);
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            
            producer.processMessage(entity);            
            log.debug("Published the employee event to customer event topic");
            
            return entity;
        }
    }
     
    public void deleteEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}
package com.sq.demo.web;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sq.demo.exception.RecordNotFoundException;
import com.sq.demo.model.EmployeeEntity;
import com.sq.demo.service.EmployeeService;
 
@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeController
{
    @Autowired
    EmployeeService service;
    
    @Autowired
    ObjectMapper mappper = new ObjectMapper();
 
    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        List<EmployeeEntity> list = service.getAllEmployees();
        return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        EmployeeEntity entity = service.getEmployeeById(id);
        return new ResponseEntity<EmployeeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    
    @GetMapping("/name")
    public ResponseEntity<List<EmployeeEntity>> getEmployeeByName(@RequestParam("name") String name)
                                                    throws RecordNotFoundException {
        List<EmployeeEntity> employees = service.getEmployeesByFirstName(name);
        return ResponseEntity.ok().body(employees);
    }
 
    @PostMapping
    public ResponseEntity<EmployeeEntity> createOrUpdateEmployee(@RequestBody @Valid EmployeeEntity employee)
                                                    throws RecordNotFoundException, JsonParseException, JsonMappingException, IOException {
        
        EmployeeEntity updated = service.createOrUpdateEmployee(employee);
        return ResponseEntity.ok().body(updated);
    }
    
    @PutMapping("/id")
    public ResponseEntity<EmployeeEntity> updateEmployee(@RequestBody EmployeeEntity employee) throws RecordNotFoundException, JsonProcessingException
                                                     {
        
        EmployeeEntity updated = service.createOrUpdateEmployee(employee);
        return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteEmployeeById(id);
        return HttpStatus.FORBIDDEN;
    }
 
}
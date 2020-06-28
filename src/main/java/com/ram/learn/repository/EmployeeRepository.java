package com.ram.learn.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ram.learn.model.EmployeeEntity;
 
@Repository
public interface EmployeeRepository
        extends JpaRepository<EmployeeEntity, Long> , EmployeeRepositoryCustom {
    List<EmployeeEntity> getEmployeesByFirstName(String name);
}

package com.sq.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sq.demo.model.EmployeeEntity;
 
@Repository
public interface EmployeeRepository
        extends JpaRepository<EmployeeEntity, Long> , EmployeeRepositoryCustom {
    List<EmployeeEntity> getEmployeesByFirstName(String name);
}

package com.ram.learn.repository;

import java.util.List;

import com.ram.learn.model.EmployeeEntity;

public interface EmployeeRepositoryCustom {
   List<EmployeeEntity>  getEmployeeswithByName(String name);
   
}

package com.sq.demo.repository;

import java.util.List;

import com.sq.demo.model.EmployeeEntity;

public interface EmployeeRepositoryCustom {
   List<EmployeeEntity>  getEmployeeswithByName(String name);
   
}

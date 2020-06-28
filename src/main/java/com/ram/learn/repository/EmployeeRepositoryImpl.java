package com.ram.learn.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ram.learn.model.EmployeeEntity;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {
    
    @Autowired
    EntityManager em;

    @Override
    public List<EmployeeEntity> getEmployeeswithByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> cq = cb.createQuery(EmployeeEntity.class);
     
        
        
        Root<EmployeeEntity> employee = cq.from(EmployeeEntity.class);
        List<Predicate> predicates = new ArrayList<>();
         
        
        if (name != null) {
            predicates.add(cb.like(employee.get("firstName"), "%" + name + "%"));
        }
        cq.where(predicates.toArray(new Predicate[0]));
     
        return em.createQuery(cq).getResultList();
    }

}

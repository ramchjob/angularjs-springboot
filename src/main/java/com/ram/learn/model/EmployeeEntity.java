package com.ram.learn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name="TBL_EMPLOYEES")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(name="first_name")
    private String firstName;
    
    @NotBlank
    @Column(name="last_name")
    private String lastName;
    
    @Column(name="email", nullable=false, length=200)
    private String email;
    
    @Column(name="dept_id") 
    private Integer deptId;
    
    
    
    /*
     * @OneToOne
     * 
     * @JoinColumn(name="dept_id") private DepartmentEntity department;
     * 
     * 
     * public DepartmentEntity getDepartment() { return department; }
     * 
     * public void setDepartment(DepartmentEntity department) { this.department =
     * department; }
     */
    
}
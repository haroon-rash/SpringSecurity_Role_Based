package org.example.springsecurity.springsecurity.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id",unique = true,nullable = false)

    private Long emp_id;


    @Column(name="name",nullable = false)
    private String emp_name;
    @Column(name="salary",nullable = false)
    private double emp_salary;
    @Column(name="joining date",nullable = false)
    private Date emp_joining_date;
    @Column(name="password",nullable = false)
private String emp_password;
    @Column(name="role",nullable = false)
    private String emp_role;
    public Employee(Long emp_id, String emp_name, double emp_salary, Date emp_joining_date) {


        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_salary = emp_salary;
        this.emp_joining_date = emp_joining_date;
    }

    public Employee() {
        super();
    }
}

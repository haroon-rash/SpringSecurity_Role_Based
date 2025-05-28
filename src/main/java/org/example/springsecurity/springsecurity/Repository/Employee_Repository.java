package org.example.springsecurity.springsecurity.Repository;

import org.example.springsecurity.springsecurity.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface Employee_Repository extends JpaRepository <Employee,Long>{
    @Query("SELECT e FROM Employee e WHERE e.emp_name = :name")
    Optional<Employee> findEmployeeByEmp_name(String name);
}

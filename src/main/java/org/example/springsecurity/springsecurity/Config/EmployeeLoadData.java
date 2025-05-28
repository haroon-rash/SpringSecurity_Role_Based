package org.example.springsecurity.springsecurity.Config;


import ch.qos.logback.core.encoder.Encoder;
import org.example.springsecurity.springsecurity.Entity.Employee;
import org.example.springsecurity.springsecurity.Repository.Employee_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class EmployeeLoadData implements UserDetailsService {

   @Autowired
    Employee_Repository emp_Repository;

    public EmployeeLoadData( Employee_Repository emp_Repository) {

        this.emp_Repository = emp_Repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

       Optional<Employee> emp= emp_Repository.findEmployeeByEmp_name(username);

       if(emp.isPresent()){
           var employee=emp.get();
           return User.builder()
                   .username(employee.getEmp_name())
                   .password(employee.getEmp_password())
                   .roles(employee.getEmp_role())
                   .build();
       }
          throw new UsernameNotFoundException("User not found: " + emp);
    }




}

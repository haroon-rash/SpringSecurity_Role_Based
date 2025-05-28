package org.example.springsecurity.springsecurity.Controller;

import org.example.springsecurity.springsecurity.Entity.Employee;
import org.example.springsecurity.springsecurity.Service.EmpServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterConroller {


    EmpServices empServices;

    private final PasswordEncoder pass;
    public RegisterConroller(EmpServices empServices, PasswordEncoder pass) {
        this.empServices = empServices;
        this.pass = pass;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Employee employee){
        employee.setEmp_password(pass.encode(employee.getEmp_password()));
        if( empServices.saveEmployee(employee)){
            return ResponseEntity.ok("Success "+employee);
        }
        return ResponseEntity.badRequest().body("Failed");

    }


    }

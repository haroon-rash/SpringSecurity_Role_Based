package org.example.springsecurity.springsecurity.Service;

import org.example.springsecurity.springsecurity.Entity.Employee;
import org.example.springsecurity.springsecurity.Repository.Employee_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpServices {

    Employee_Repository emp_Repository;

    EmpServices(Employee_Repository emp_Repository){
        this.emp_Repository=emp_Repository;
    }

    public boolean saveEmployee(Employee employee){

        Employee emp=emp_Repository.save(employee);
         if(emp!=null){
         return true;
         }
         return false;
    }

}

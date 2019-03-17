package com.springboot.demo.api;

import com.springboot.demo.jpa.entity.Employee;
import com.springboot.demo.jpa.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employees=employeeRepository.findAll();
        if(employees==null ||employees.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    @PostMapping("add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
       Employee addedEmployee= employeeRepository.save(employee);

       if(addedEmployee==null){
           return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(addedEmployee,HttpStatus.CREATED);
    }

}

package com.springboot.demo.jpa.repo;

import com.springboot.demo.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}

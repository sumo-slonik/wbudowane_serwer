package com.example.wbudowane_serwer.Repos;

import com.example.wbudowane_serwer.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> getAllBy();
}

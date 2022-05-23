package com.example.wbudowane_serwer.Services;

import com.example.wbudowane_serwer.Entities.Employee;
import com.example.wbudowane_serwer.Repos.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee toSave){
        return employeeRepository.save(toSave);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.getAllBy();
    }
}

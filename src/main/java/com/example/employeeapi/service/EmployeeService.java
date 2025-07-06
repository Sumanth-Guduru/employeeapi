package com.example.employeeapi.service;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository){
        this.repository=repository;
    }

    public List<Employee> getAll(){
        return repository.findAll();
    }

    public Optional<Employee> getById(Long id){
        return repository.findById(id);
    }

    public Employee save(Employee emp) {
        return repository.save(emp);
    }
    public void delete(Long id){
        repository.deleteById(id);
    }
}

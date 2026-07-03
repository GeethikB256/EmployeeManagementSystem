package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();

    }

    public Employee createEmployee(Employee employee){

        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id){

        return employeeRepository.findById(id).orElseThrow(()
                ->new EntityNotFoundException("Employee id doesn't exist"));
    }


    public Employee updateEmployee(Long id, Employee updatedEmployee){
        return employeeRepository.findById(id).map(employee -> {
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setDepartment(updatedEmployee.getDepartment());
            employee.setJobTitle(updatedEmployee.getJobTitle());
            employee.setSalary(updatedEmployee.getSalary());
            return employeeRepository.save(employee);

        }).orElseThrow(() ->new EntityNotFoundException("Employee id doesn't exist"));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.findById(id).orElseThrow(()
                ->new EntityNotFoundException("Employee id doesn't exist"));
        employeeRepository.deleteById(id);

    }
}

package com.example.EmployeeManagementSystem.ServiceTest;

import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    public void setUp(){
        employee = new Employee(1L, "Ram", "Rolex",
                "ram@gmail.com", "QA", "Tester", 50000.0 );
    }
    @Test
    public void saveEmployeeTest(){
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        Employee savedEmployee = employeeService.createEmployee(employee);
        assertEquals("Ram", savedEmployee.getFirstName());
    }
    @Test
    public void saveEmployeeWithNullFieldsTest() {
        Employee invalidEmployee = new Employee(null, null, null, null, null, null, null);
        when(employeeRepository.save(any(Employee.class))).thenReturn(invalidEmployee);
        Employee savedEmployee = employeeService.createEmployee(invalidEmployee);
        assertNull(savedEmployee.getFirstName());
    }
    @Test
    public void getAllEmployeesTest(){
        when(employeeRepository.findAll()).thenReturn(List.of(employee));
        List<Employee> employeeList = employeeService.getAllEmployees();
        assertEquals("Rolex",employeeList.get(0).getLastName());
    }
    @Test
    public void getAllEmployeesEmptyTest() {
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());
        List<Employee> employeeList = employeeService.getAllEmployees();
        assertTrue(employeeList.isEmpty());
    }
    @Test
    public void getEmployeeByIdTest(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Employee actualEmployee = employeeService.getEmployeeById(1L);
        assertEquals("ram@gmail.com",actualEmployee.getEmail());
    }

    @Test
    public void updateEmployeeTest(){
        Employee upadteEmployee = new Employee(1L, "Rohith", "Sharma",
                "rohith@gmail.com", "RA", "Tester", 25000.0 );
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(upadteEmployee);
        Employee actualEmployee  = employeeService.updateEmployee(1L, upadteEmployee);
        assertEquals("RA", actualEmployee.getDepartment());
    }

    @Test
    public void deleteEmployeeTest(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).deleteById(1L);
        employeeService.deleteEmployee(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }
    @Test
    public void deleteEmployeeWithInvalidIdTest() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> employeeService.deleteEmployee(99L));
    }
}


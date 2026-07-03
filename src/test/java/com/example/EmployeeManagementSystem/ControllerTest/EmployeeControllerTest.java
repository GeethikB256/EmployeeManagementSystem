package com.example.EmployeeManagementSystem.ControllerTest;

import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;
    private Employee employee;
    @BeforeEach
    public void setUp() {
        employeeRepository.deleteAll();
        employee = new Employee(null, "John", "Doe", "john@example.com", "IT", "Developer", 50000.00);
        employee = employeeRepository.save(employee);
    }

    @Test
    void testCreateEmployee() throws Exception {
        employeeRepository.deleteAll();
        Employee employee1 = new Employee(null, "John", "Doe", "john@example.com", "IT", "Developer", 50000.00);
        mockMvc.perform(post("/api/employees/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void testGetAllEmployees() throws Exception {
        mockMvc.perform(get("/api/employees/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }


    @Test
    void testGetEmployeeById() throws Exception {
        mockMvc.perform(get("/api/employees/" + employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Employee updated = new Employee(null, "New", "Name", "new@example.com", "HR", "Manager", 60000.00);

        mockMvc.perform(put("/api/employees/update/" + employee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("new@example.com"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/employees/delete/" + employee.getId()))
                .andExpect(status().isNoContent());
    }
}



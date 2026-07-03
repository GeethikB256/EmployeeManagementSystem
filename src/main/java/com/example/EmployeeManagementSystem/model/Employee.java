package com.example.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.persistence.Id;

@Entity
@Table(name= "employees",uniqueConstraints = @UniqueConstraint(columnNames="email"))
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    @Column(name = "firstName", nullable = false, length = 35)
    private String firstName;

    @NotBlank(message = "last name cannot be blank")
    @Column(name = "lastName", nullable = false, length = 20)
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email must be valid")
    @NotBlank(message = "email cannot be blank")
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @NotBlank(message = "Department cannot be blank")
    @Column(nullable = false, length = 45)
    private String department;

    @NotBlank(message = "Job Title cannot be blank")
    @Column(name = "jobTitle", nullable = false, length = 30)
    private String jobTitle;

    @Positive(message = "salary must be greater than zero")
    private Double salary;

    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, String email, String department, String jobTitle, Double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
package com.example.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name="employee_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRegister {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="employee_name", nullable = false)
    private String name;

    @Column(name="employee_email", nullable = false, unique = true)
    private String email;

    @Column(name="employee_date_of_birth")
    private LocalDate dob;

    @Column(name="employee_joining_date")
    private LocalDate joiningDate;

    @Column(name="employee_department", nullable = false)
    private String department;

    @Column(name="employee_position", nullable = false)
    private String position;
}

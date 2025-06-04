package com.example.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor; // Add NoArgsConstructor
import lombok.AllArgsConstructor; // Add AllArgsConstructor
import java.time.LocalDate; // Import LocalDate

@Entity
@Table(name="employee_list") // Use @Table for table name, @Entity(name) is for JPQL queries
@Data
@NoArgsConstructor // Good practice for JPA entities
@AllArgsConstructor // Good practice for JPA entities if you use all-args constructor
public class EmployeeRegister {

    @Id
    @Column(name = "id") // Conventionally lowercase
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use Long for auto-generated IDs

    @Column(name="employee_name", nullable = false) // Add nullable=false if required
    private String name;

    @Column(name="employee_email", nullable = false, unique = true) // Add unique=true for email
    private String email;

    @Column(name="employee_date_of_birth")
    private LocalDate dob; // Changed to LocalDate

    @Column(name="employee_joining_date")
    private LocalDate joiningDate; // Changed to LocalDate

    @Column(name="employee_department", nullable = false)
    private String department;

    @Column(name="employee_position", nullable = false)
    private String position;
}

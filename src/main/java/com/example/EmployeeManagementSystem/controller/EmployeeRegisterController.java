package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.model.EmployeeRegister;
import com.example.EmployeeManagementSystem.service.EmployeeRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:4200") // IMPORTANT: Use double quotes for the string
@RestController
@RequestMapping("/api/employees") // Base path for all employee-related operations
@RequiredArgsConstructor
public class EmployeeRegisterController {

    private final EmployeeRegisterService service;

    /**
     * Handles POST requests to create a new employee.
     * Maps to: POST http://localhost:8080/api/employees
     * @param employee The employee object sent from the client.
     * @return ResponseEntity with the saved employee and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<EmployeeRegister> addEmployee(@RequestBody EmployeeRegister employee) {
        EmployeeRegister savedEmployee = service.addEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    /**
     * Handles GET requests to retrieve all employees.
     * Maps to: GET http://localhost:8080/api/employees
     * @return ResponseEntity with a list of employees and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<EmployeeRegister>> getallEmployee() {
        List<EmployeeRegister> employees = service.getallEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    /**
     * Handles GET requests to retrieve an employee by ID.
     * Maps to: GET http://localhost:8080/api/employees/{id}
     * @param id The ID of the employee to retrieve.
     * @return ResponseEntity with the employee and HTTP status 200 (OK), or 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeRegister> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeRegister> employee = service.getEmployeeById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Handles PUT requests to update an existing employee.
     * Maps to: PUT http://localhost:8080/api/employees/{id}
     * @param id The ID of the employee to update.
     * @param updatedEmployee The updated employee object sent from the client.
     * @return ResponseEntity with the updated employee and HTTP status 200 (OK), or 404 (Not Found).
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeRegister> updateEmployee(@PathVariable Long id,
                                                           @RequestBody EmployeeRegister updatedEmployee) {
        try {
            EmployeeRegister updated = service.updateEmployee(id, updatedEmployee);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            // Log the exception for debugging on the server side
            System.err.println("Error updating employee: " + ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Handles DELETE requests to delete an employee by ID.
     * Maps to: DELETE http://localhost:8080/api/employees/{id}
     * @param id The ID of the employee to delete.
     * @return ResponseEntity with no content and HTTP status 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

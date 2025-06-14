package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.model.EmployeeRegister;
import com.example.EmployeeManagementSystem.service.EmployeeRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeRegisterController {

    private final EmployeeRegisterService service;


    @PostMapping
    public ResponseEntity<EmployeeRegister> addEmployee(@RequestBody EmployeeRegister employee) {
        EmployeeRegister savedEmployee = service.addEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<EmployeeRegister>> getallEmployee() {
        List<EmployeeRegister> employees = service.getallEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeRegister> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeRegister> employee = service.getEmployeeById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmployeeRegister> updateEmployee(@PathVariable Long id,
                                                           @RequestBody EmployeeRegister updatedEmployee) {
        try {
            EmployeeRegister updated = service.updateEmployee(id, updatedEmployee);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            System.err.println("Error updating employee: " + ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

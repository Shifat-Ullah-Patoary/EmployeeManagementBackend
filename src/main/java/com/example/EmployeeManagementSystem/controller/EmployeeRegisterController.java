package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.model.EmployeeRegister;
import com.example.EmployeeManagementSystem.service.EmployeeRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class EmployeeRegisterController {

    private final EmployeeRegisterService service;


    @PostMapping("/addEmployee")
    public EmployeeRegister addEmployee(@RequestBody EmployeeRegister employee) {
        return service.addEmployee(employee);
    }

    @GetMapping("/getEmployeeList")
    public List<EmployeeRegister> getallEmployee() {
        return service.getallEmployee();
    }

    @GetMapping("/{id}")
    public Optional<EmployeeRegister> getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeRegister> updateEmployee(@PathVariable Long id,
                                                           @RequestBody EmployeeRegister updatedEmployee) {
        try {
            EmployeeRegister updated = service.updateEmployee(id, updatedEmployee);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

        @DeleteMapping("/delete/{id}")
        public void deleteEmployee(@PathVariable Long id) {
            service.deleteEmployee(id);
        }
    }





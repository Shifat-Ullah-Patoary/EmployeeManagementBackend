package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.model.EmployeeRegister;
import com.example.EmployeeManagementSystem.repository.EmployeeRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeRegisterService {
    private final EmployeeRegisterRepository repo;

    public EmployeeRegister addEmployee(EmployeeRegister employee) {
        return repo.save(employee);
    }

    public List<EmployeeRegister> getallEmployee() {
        return repo.findAll();
    }

    public Optional<EmployeeRegister> getEmployeeById(Long id) {
        return repo.findById(id);
    }

    public void deleteEmployee(Long id) {
        repo.deleteById(id);
    }

    public EmployeeRegister updateEmployee(Long id, EmployeeRegister updatedEmployee) {
        return repo.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setName(updatedEmployee.getName());
                    existingEmployee.setEmail(updatedEmployee.getEmail());
                    existingEmployee.setDob(updatedEmployee.getDob());
                    existingEmployee.setJoiningDate(updatedEmployee.getJoiningDate());
                    existingEmployee.setDepartment(updatedEmployee.getDepartment());
                    existingEmployee.setPosition(updatedEmployee.getPosition());
                    return repo.save(existingEmployee);
                })
                .orElseThrow(() -> new RuntimeException("Employee with ID " + id + " not found"));
    }
}





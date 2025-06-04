package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.model.EmployeeRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRegisterRepository extends JpaRepository<EmployeeRegister,Long> {


}

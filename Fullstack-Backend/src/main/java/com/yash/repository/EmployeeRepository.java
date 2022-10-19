package com.yash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

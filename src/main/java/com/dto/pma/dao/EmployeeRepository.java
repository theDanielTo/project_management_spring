package com.dto.pma.dao;

import com.dto.pma.entities.Employee;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}

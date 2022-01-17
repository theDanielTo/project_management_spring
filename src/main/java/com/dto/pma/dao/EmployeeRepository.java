package com.dto.pma.dao;

import java.util.List;

import com.dto.pma.entities.Employee;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
  @Override
  List<Employee> findAll();
}

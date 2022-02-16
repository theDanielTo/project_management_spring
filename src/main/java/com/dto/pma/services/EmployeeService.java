package com.dto.pma.services;

import com.dto.pma.dao.EmployeeRepository;
import com.dto.pma.dto.EmployeeProject;
import com.dto.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

  @Autowired
  EmployeeRepository empRepo;

  public void save(Employee employee) {
    empRepo.save(employee);
  }

  public List<Employee> getAll() {
    return empRepo.findAll();
  }

  public List<EmployeeProject> employeeProjects() {
    return empRepo.employeeProjects();
  }
}

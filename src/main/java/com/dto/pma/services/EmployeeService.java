package com.dto.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  // Field Injection
  @Qualifier("staffRepositoryImpl1")
  @Autowired
  IStaffRepository empRepo;
  // EmployeeRepository empRepo;

  // Constructor Injection
  // public EmployeeService(EmployeeRepository empRepo) {
  //   super();
  //   this.empRepo = empRepo;
  // }
  // @Qualifier == @Primary (consumed first)
  // public EmployeeService(IStaffRepository empRepo) {
  //   super();
  //   this.empRepo = empRepo;
  // }

  // Setter Injection
  @Autowired
  public void setEmpRepo(IStaffRepository empRepo) {
    this.empRepo = empRepo;
  }
}

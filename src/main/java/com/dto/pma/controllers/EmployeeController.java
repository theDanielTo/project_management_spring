package com.dto.pma.controllers;

import java.util.List;

import com.dto.pma.dao.EmployeeRepository;
import com.dto.pma.entities.Employee;

import com.dto.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired
  EmployeeService empService;

  @GetMapping
  public String displayProjectsList(Model model) {
    List<Employee> employees = empService.getAll();
    model.addAttribute("employeesList", employees);
    return "employees/employees-list";
  }

  @GetMapping("/new")
  public String displayEmployeeForm(Model model) {
    Employee newEmployee = new Employee();
    model.addAttribute("employee", newEmployee);

    return "employees/new-employee";
  }

  @PostMapping("/save")
  public String createEmployee(Employee employee, Model model) {
    empService.save(employee);

    return "redirect:/employees";
  }
}

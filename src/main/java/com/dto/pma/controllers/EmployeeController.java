package com.dto.pma.controllers;

import com.dto.pma.dao.EmployeeRepository;
import com.dto.pma.entities.Employee;

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
  EmployeeRepository empRepo;

  @GetMapping("/new")
  public String displayEmployeeForm(Model model) {
    Employee newEmployee = new Employee();
    model.addAttribute("employee", newEmployee);

    return "new-employee";
  }

  @PostMapping("/save")
  public String createEmployee(Employee employee, Model model) {
    empRepo.save(employee);

    return "redirect:/employees/new";
  }
}

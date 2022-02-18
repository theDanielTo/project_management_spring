package com.dto.pma.controllers;

import com.dto.pma.entities.Employee;

import com.dto.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired
  EmployeeService empService;

  @GetMapping
  public String displayProjectsList(Model model) {
    Iterable<Employee> employees = empService.getAll();
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
  public String createEmployee(Model model, @Valid Employee employee, Errors errors) {
    if (errors.hasErrors()) {
      return "employees/new-employee";
    }

    empService.save(employee);

    return "redirect:/employees";
  }

  @GetMapping("/update")
  public String displayEmployeeUpdateForm(@RequestParam("id") long id, Model model) {
    Employee emp = empService.findById(id);
    model.addAttribute("employee", emp);

    return "employees/new-employee"; // same form for creating new employee
  }

  @GetMapping("/delete")
  public String deleteEmployee(@RequestParam("id") long id, Model model) {
    Employee emp = empService.findById(id);
    empService.delete(emp);

    return "redirect:/employees";
  }
}

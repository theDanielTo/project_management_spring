package com.dto.pma.controllers;

import java.util.List;

import com.dto.pma.dao.EmployeeRepository;
import com.dto.pma.dao.ProjectRepository;
import com.dto.pma.entities.Employee;
import com.dto.pma.entities.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @Autowired
  ProjectRepository projRepo;

  @Autowired
  EmployeeRepository empRepo;

  @GetMapping("/")
  public String displayHome(Model model) {
    // querying the DB for projects & employees
    List<Project> projects = projRepo.findAll();
    model.addAttribute("projectsList", projects);

    List<Employee> employees = empRepo.findAll();
    model.addAttribute("employeesList", employees);

    return "home";
  }
}

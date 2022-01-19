package com.dto.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dto.pma.dao.EmployeeRepository;
import com.dto.pma.dao.ProjectRepository;
import com.dto.pma.dto.ChartData;
import com.dto.pma.dto.EmployeeProject;
import com.dto.pma.entities.Project;
import com.dto.pma.springExample.Car;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @Autowired
  Car car;

  @Autowired
  ProjectRepository projRepo;

  @Autowired
  EmployeeRepository empRepo;

  @GetMapping("/")
  public String displayHome(Model model) throws JsonProcessingException {

    Map<String, Object> map = new HashMap<>();

    // querying the DB for projects & employees
    List<Project> projects = projRepo.findAll();
    model.addAttribute("projectsList", projects);

    List<ChartData> projectData = projRepo.getProjectStatus();
    // let's convert projectData object into a JSON structure for use in javascript
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonStr = objectMapper.writeValueAsString(projectData);
    model.addAttribute("projectStatusCnt", jsonStr);

    // querying the DB for employees
    List<EmployeeProject> employeesProjectCount = empRepo.employeeProjects();
    model.addAttribute("employeesProjectCount", employeesProjectCount);

    return "main/home";
  }
}

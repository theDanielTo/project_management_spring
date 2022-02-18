package com.dto.pma.controllers;

import java.util.List;

import com.dto.pma.entities.Employee;
import com.dto.pma.entities.Project;

import com.dto.pma.services.EmployeeService;
import com.dto.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/projects")
public class ProjectController {

  @Autowired
  ProjectService projService;

  @Autowired
  EmployeeService empService;

  @GetMapping
  public String displayProjectsList(Model model) {
    Iterable<Project> projects = projService.getAll();
    model.addAttribute("projectsList", projects);
    return "projects/projects-list";
  }

  @GetMapping("/new")
  public String displayProjectForm(Model model) {

    Project aProject = new Project();
    Iterable<Employee> allEmployees = empService.getAll();
    model.addAttribute("project", aProject);
    model.addAttribute("allEmployees", allEmployees);

    return "projects/new-project";
  }

  @PostMapping("/save")
  public String createProject(Project project, Model model) {
    projService.save(project);

    // use a redirect to prevent duplicate submissions
    return "redirect:/projects";
  }

  @GetMapping("/update")
  public String updateProject(@RequestParam("id") long id, Model model) {
    Project project = projService.getById(id);
    model.addAttribute("project", project);

    return "projects/new-project";
  }

  @GetMapping("/delete")
  public String deleteProject(@RequestParam("id") long id, Model model) {
    Project project = projService.getById(id);
    projService.delete(project);

    return "redirect:/projects";
  }
}

package com.dto.pma.controllers;

import java.util.List;

import com.dto.pma.dao.ProjectRepository;
import com.dto.pma.entities.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {

  @Autowired
  ProjectRepository projRepo;

  @GetMapping
  public String displayProjectsList(Model model) {
    List<Project> projects = projRepo.findAll();
    model.addAttribute("projectsList", projects);
    return "projects/projects-list";
  }

  @GetMapping("/new")
  public String displayProjectForm(Model model) {
    Project aProject = new Project();
    model.addAttribute("project", aProject);

    return "projects/new-project";
  }

  @PostMapping("/save")
  public String createProject(Project project, Model model) {
    projRepo.save(project);

    // use a redirect to prevent duplicate submissions
    return "redirect:/projects/new";
  }
}

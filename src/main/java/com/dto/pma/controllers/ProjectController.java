package com.dto.pma.controllers;

import com.dto.pma.entities.Project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {

  @GetMapping("/new")
  public String displayProjectForm(Model model) {

    Project aProject = new Project();

    model.addAttribute("project", aProject);

    return "new-project";
  }

  @PostMapping("/save")
  public String createProject(Model model) {
    // this should handle saving to the DB
    return "";
  }
}

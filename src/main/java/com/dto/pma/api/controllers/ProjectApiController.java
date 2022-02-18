package com.dto.pma.api.controllers;

import com.dto.pma.dao.ProjectRepository;
import com.dto.pma.entities.Employee;
import com.dto.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

    @Autowired
    ProjectRepository projRepo;

    @GetMapping
    public Iterable<Project> getProjects() {
        return projRepo.findAll();
    }

    @GetMapping("/{id}")
    public Project getById(@PathVariable("id") Long id) {
        return projRepo.findById(id).get();
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody Project project) {
        return projRepo.save(project);
    }

    @PutMapping(consumes="application/json") // consumes not need. it is implied by Spring
    @ResponseStatus(HttpStatus.OK)
    public Project update(@RequestBody Project project) {
        return projRepo.save(project);
    }

//    @PatchMapping(path="/{id}", consumes = "application/json")
//    public Project partialUpdate(@PathVariable("id") long id, @RequestBody Project patchProject) {
//        Project proj = projRepo.findById(id).get();
//
//        if (patchProject.getEmail() != null) {
//            proj.setEmail(patchEmployee.getEmail());
//        }
//        if (patchProject.getFirstName() != null) {
//            proj.setFirstName(patchEmployee.getFirstName());
//        }
//        if (patchProject.getEmail() != null) {
//            proj.setLastName(patchEmployee.getLastName());
//        }
//
//        return projRepo.save(emp);
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        try {
            projRepo.deleteById(id);
        } catch(EmptyResultDataAccessException ignored) {

        }
    }
}
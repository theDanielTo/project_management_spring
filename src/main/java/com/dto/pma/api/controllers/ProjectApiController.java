package com.dto.pma.api.controllers;

import com.dto.pma.dao.ProjectRepository;
import com.dto.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Project create(@RequestBody @Valid Project project) {
        return projRepo.save(project);
    }

    @PutMapping(consumes="application/json") // consumes not need. it is implied by Spring
    @ResponseStatus(HttpStatus.OK)
    public Project update(@RequestBody Project project) {
        return projRepo.save(project);
    }

    @PatchMapping(path="/{id}", consumes = "application/json")
    public Project partialUpdate(@PathVariable("id") long id, @RequestBody @Valid Project patchProject) {
        Project proj = projRepo.findById(id).get();

        if (patchProject.getName() != null) {
            proj.setName(patchProject.getName());
        }
        if (patchProject.getStage() != null) {
            proj.setStage(patchProject.getStage());
        }
        if (patchProject.getDescription() != null) {
            proj.setDescription(patchProject.getDescription());
        }

        return projRepo.save(proj);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        try {
            projRepo.deleteById(id);
        } catch(EmptyResultDataAccessException ignored) {
            // prevent exception
        }
    }

    // Pagination
    // Get responses by page and size
    @GetMapping(params = {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Project> findPaginatedEmployees(@RequestParam("page") int page,
                                                    @RequestParam("size") int size) {
        Pageable pageAndSize = PageRequest.of(page, size);
        return projRepo.findAll(pageAndSize);
    }
}

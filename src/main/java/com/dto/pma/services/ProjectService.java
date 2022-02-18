package com.dto.pma.services;

import com.dto.pma.dao.ProjectRepository;
import com.dto.pma.dto.ChartData;
import com.dto.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projRepo;

    public void save(Project project) {
        projRepo.save(project);
    }

    public Iterable<Project> getAll() {
        return projRepo.findAll();
    }

    public List<ChartData> getProjectStatus() {
        return projRepo.getProjectStatus();
    }
}

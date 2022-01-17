package com.dto.pma.dao;

import java.util.List;

import com.dto.pma.entities.Project;

import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
  @Override
  List<Project> findAll();
}

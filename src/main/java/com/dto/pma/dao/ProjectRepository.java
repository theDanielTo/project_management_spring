package com.dto.pma.dao;

import java.util.List;

import com.dto.pma.dto.ChartData;
import com.dto.pma.entities.Project;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="apiprojects", path="apiprojects")
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

  @Override
  public List<Project> findAll();

  @Query(nativeQuery=true,
    value="SELECT stage as label, COUNT(*) as value " +
      "FROM project " +
      "GROUP BY stage")
  List<ChartData> getProjectStatus();

  Project getByProjectId(long id);
}

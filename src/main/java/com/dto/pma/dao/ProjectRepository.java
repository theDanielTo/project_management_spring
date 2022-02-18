package com.dto.pma.dao;

import java.util.List;

import com.dto.pma.dto.ChartData;
import com.dto.pma.entities.Project;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="apiemployees", path="apiemployees")
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

  @Query(nativeQuery=true,
    value="SELECT stage as label, COUNT(*) as value " +
      "FROM project " +
      "GROUP BY stage")
  List<ChartData> getProjectStatus();

  public Project getByProjectId(long id);
}

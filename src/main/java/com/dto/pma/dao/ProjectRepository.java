package com.dto.pma.dao;

import java.util.List;

import com.dto.pma.dto.ChartData;
import com.dto.pma.entities.Project;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

  @Query(nativeQuery=true,
    value="SELECT stage as label, COUNT(*) as value " +
      "FROM project " +
      "GROUP BY stage")
  List<ChartData> getProjectStatus();
}

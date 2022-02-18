package com.dto.pma.dao;

import com.dto.pma.ProjectManagementSpringApplication;
import com.dto.pma.dao.ProjectRepository;
import com.dto.pma.entities.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = ProjectManagementSpringApplication.class)
@RunWith(SpringRunner.class)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = {"classpath:schema.sql", "classpath:data.sql"}), // load schema.sql & data.sql before execution
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = "classpath:drop.sql") // script for dropping tables after testing execution
})
public class ProjectRepositoryIntegrationTest {

    @Autowired
    ProjectRepository projRepo;

    @Test
    public void ifNewProjectSaved_thenSuccess() {
        Project newProject = new Project("New Test Project", "COMPLETE", "Test Description");
        projRepo.save(newProject);

        assertEquals(5, projRepo.count()); // 4 initial projects + 1 new project
    }
}

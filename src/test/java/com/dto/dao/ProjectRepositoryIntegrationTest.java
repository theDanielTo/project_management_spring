package com.dto.dao;

import com.dto.pma.ProjectManagementSpringApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes = ProjectManagementSpringApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = {"classpath:schema.sql", "classpath:data.sql"}), // load schema.sql & data.sql before execution
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = "classpath:drop.sql")
})
public class ProjectRepositoryIntegrationTest {

}

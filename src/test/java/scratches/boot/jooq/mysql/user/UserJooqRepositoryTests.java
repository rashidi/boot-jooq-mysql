package scratches.boot.jooq.mysql.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;
import static org.testcontainers.containers.MySQLContainer.IMAGE;

/**
 * @author Rashidi Zin
 */
@Testcontainers
@Sql(scripts = "file:mysql/init/init.sql")
@JooqTest(includeFilters = @Filter(type = ASSIGNABLE_TYPE, classes = UserJooqRepository.class))
class UserJooqRepositoryTests {

    @Container
    static MySQLContainer<?> container = new MySQLContainer<>(IMAGE + ":8")
            .withPassword("mysql")
            .withDatabaseName("demo");

    @Autowired
    private UserJooqRepository repository;

    @DynamicPropertySource
    static void datasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }

    @Test
    @DisplayName("Username rashidi, rudyard.kipling, and robert.frost are found in the database")
    void findByUsername() {
        Stream
                .of("rashidi", "rudyard.kipling", "robert.frost")
                .forEach(username -> {
                    var record = repository.findByUsername(username);

                    assertThat(record).isNotNull();
                });
    }

}

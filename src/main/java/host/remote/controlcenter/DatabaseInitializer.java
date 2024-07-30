package host.remote.controlcenter;

import host.remote.controlcenter.config.DatasourceConfig;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * This class implements ApplicationContextInitializer, which means its initialize method
 * will be executed on application startup, BEFORE the Spring
 * context initialization.
 * Implements the Ordered interface to set the order of execution.
 */
public class DatabaseInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Environment env = applicationContext.getEnvironment();
        if (!isDatabaseExist(env)) {
            createNewDatabase(env);
        }
    }

    private boolean isDatabaseExist(Environment env)
    {
        DataSource dataSource = DatasourceConfig.getDataSourceWithDatabase(env);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            jdbcTemplate.execute("SELECT 1");
            System.out.println("Database exists and is accessible.");
            return true;
        } catch (Exception e) {
            System.err.println("Database does not exist or is not accessible: " + e.getMessage());
            return false;
        }
    }

    private void createNewDatabase(Environment env)
    {
        DataSource dataSource = DatasourceConfig.getDataSourceWithoutDatabase(env);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            jdbcTemplate.execute(String.format("CREATE DATABASE IF NOT EXISTS %s;", env.getProperty("spring.application.name")));
            System.out.println("New database created.");
        } catch (Exception e) {
            System.err.println("Database was not created: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE; // Lower value means higher precedence
    }
}

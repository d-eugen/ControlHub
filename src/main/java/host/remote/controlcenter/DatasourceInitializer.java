package host.remote.controlcenter;

import host.remote.controlcenter.config.MySqlDatasourceConfig;
import host.remote.controlcenter.config.MySqlCondition;
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
public class DatasourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Environment env = applicationContext.getEnvironment();
        DatabaseInitializer initializer = getDatabaseInitializer(env);
        if (initializer != null && !initializer.isDatabaseExist()) {
            initializer.createNewDatabase();
        }
    }

    private DatabaseInitializer getDatabaseInitializer(Environment env)
    {
        if (MySqlCondition.isMySqlCurrentDatasource(env)){
            return new MySqlDatabaseInitializer(env);
        }
        return null;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE; // Lower value means higher precedence
    }

    interface DatabaseInitializer
    {
        boolean isDatabaseExist();
        void createNewDatabase();
    }

    static class MySqlDatabaseInitializer implements DatabaseInitializer
    {
        private final Environment environment;
        MySqlDatabaseInitializer(Environment environment) {
            this.environment = environment;
        }
        @Override
        public boolean isDatabaseExist() {
            DataSource dataSource = MySqlDatasourceConfig.getDataSourceWithDatabase(environment);
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

        @Override
        public void createNewDatabase() {
            DataSource dataSource = MySqlDatasourceConfig.getDataSourceWithoutDatabase(environment);
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            try {
                jdbcTemplate.execute(String.format("CREATE DATABASE IF NOT EXISTS %s;", environment.getProperty("spring.application.name")));
                System.out.println("New database created.");
            } catch (Exception e) {
                System.err.println("Database was not created: " + e.getMessage());
                System.exit(1);
            }
        }
    }
}

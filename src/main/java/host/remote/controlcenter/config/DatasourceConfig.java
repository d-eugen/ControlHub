package host.remote.controlcenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@Conditional(MySqlCondition.class)
public class DatasourceConfig {

    private final Environment environment;

    public DatasourceConfig(Environment env) {
        this.environment = env;
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        return getDataSourceWithDatabase(environment);
    }

    /**
     * Creates a DataSource without specifying the database.
     * This is used for checking the database existence and creating a new database if needed.
     */
    public static DataSource getDataSourceWithoutDatabase(Environment env)
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    /**
     * Creates a DataSource with the database specified in the application properties.
     * This is used as the primary data source for the application.
     */
    public static DataSource getDataSourceWithDatabase(Environment env)
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(env.getProperty("spring.datasource.url") + "/" + env.getProperty("spring.application.name"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }
}

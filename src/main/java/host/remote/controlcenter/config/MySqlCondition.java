package host.remote.controlcenter.config;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MySqlCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if (isMySqlCurrentDatasource(context.getEnvironment())) {
            return ConditionOutcome.match("Datasource is MySql");
        }
        return ConditionOutcome.noMatch("Datasource is not MySql");
    }

    public static boolean isMySqlCurrentDatasource(Environment env) {
        String url = env.getProperty("spring.datasource.url");
        return url != null && url.contains("mysql");
    }
}

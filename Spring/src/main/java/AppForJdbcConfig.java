import com.zby.config.JdbcConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class AppForJdbcConfig {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
        DataSource da = context.getBean(DataSource.class);
        System.out.println(da);

    }

}

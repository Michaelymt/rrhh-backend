package pe.com.tss.runakuna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pe.com.tss.runakuna.service.MailService;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
public class RunakunaBackendApplication {


    public static void main(String[] args) {

        SpringApplication.run(RunakunaBackendApplication.class, args);

    }


}

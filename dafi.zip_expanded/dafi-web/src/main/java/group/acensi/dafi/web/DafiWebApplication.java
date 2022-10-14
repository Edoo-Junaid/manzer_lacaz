package group.acensi.dafi.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication	(exclude = { 
	    DataSourceTransactionManagerAutoConfiguration.class, 
	    HibernateJpaAutoConfiguration.class
	})
@ComponentScan(basePackages = "group.acensi.dafi")
public class DafiWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DafiWebApplication.class, args);
	}
}

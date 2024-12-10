package app.Api.Persistence;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = {"app.Api.Repository"},
		entityManagerFactoryRef = "defaultEntityManager",
		transactionManagerRef = "defaultTransactionManager"
		)
public class DefaultPersistenceConfig {
	@Bean(name = "defaultEntityManager")
	@Primary
	public LocalContainerEntityManagerFactoryBean defaultEntity(DataSource dataSource ,EntityManagerFactoryBuilder builder ) {
		Map<String, Object> jpaProperties = new HashMap<>();
	    jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"); // or another dialect
	    jpaProperties.put("hibernate.hbm2ddl.auto", "update"); // controls schema creation
	    jpaProperties.put("hibernate.show_sql", true); // logs SQL statements
	    jpaProperties.put("hibernate.format_sql", true); // formats SQL output
		return builder
				.dataSource(dataSource)
				.packages("app.Api.Entity")
				.persistenceUnit("default")
				.properties(jpaProperties)
				.build();
				
		
	}
	@Bean(name = "defaultTransactionManager")
	@Primary
    public PlatformTransactionManager transactionManager (@Qualifier("defaultEntityManager") 
     LocalContainerEntityManagerFactoryBean entityManager ) {
		return new JpaTransactionManager(entityManager.getObject());
    	
    }
  
	
}

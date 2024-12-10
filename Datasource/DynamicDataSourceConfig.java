package app.Api.Datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DynamicDataSourceConfig {
	
	@Bean
	public DataSource dataSource() {
		DynamicRoutingDataSource dynamicrouting = new DynamicRoutingDataSource();
		
		DriverManagerDataSource defaultDataSource = new DriverManagerDataSource();
		defaultDataSource.setUrl("jdbc:mysql://localhost:3306/management");
		defaultDataSource.setUsername("root");
		defaultDataSource.setPassword("Captain254$");
		defaultDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		Map<Object,Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put("default", defaultDataSource);
		dynamicrouting.setTargetDataSources(dataSourceMap);
		
		dynamicrouting.setDefaultTargetDataSource(defaultDataSource);
		
		return dynamicrouting;
		
	
		
		
	}
	

}

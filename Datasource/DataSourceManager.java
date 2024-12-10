package app.Api.Datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
@Component
public class DataSourceManager {
	
	private final DynamicRoutingDataSource dynamicDatasource;
	
	public DataSourceManager(DataSource dataSource) {
		this.dynamicDatasource = (DynamicRoutingDataSource)dataSource;
		
	}
	public void addTenant(String tenantId) {
		DriverManagerDataSource newDataSource = new DriverManagerDataSource();
        newDataSource.setUrl("jdbc:mysql://localhost:3306/"+tenantId);
        newDataSource.setUsername("root");
        newDataSource.setPassword("Captain254$");
        newDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        

        // Add the new tenant's data source
        Map<Object, Object> targetDataSources = new HashMap<>(dynamicDatasource.getResolvedDataSources());
        targetDataSources.put(tenantId, newDataSource); // Add the new data source

        dynamicDatasource.setTargetDataSources(targetDataSources); // Update target data sources
        dynamicDatasource.afterPropertiesSet(); // Reinitialize the data source

	}
	public DynamicRoutingDataSource getDynamicDatasource() {
		return dynamicDatasource;
	}
	

}

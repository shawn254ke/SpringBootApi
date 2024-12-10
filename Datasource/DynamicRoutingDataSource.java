package app.Api.Datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		System.out.println(TenantContext.getCurrentTenant()+"@determineCurrentLookup");
		return TenantContext.getCurrentTenant();
	}
	

}

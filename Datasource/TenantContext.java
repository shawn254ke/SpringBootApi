package app.Api.Datasource;

public class TenantContext {
	private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();
	
	public static String getCurrentTenant () {
		return currentTenant.get();
	}
	
	public static void setCurrentTenant(String TenantId) {
		currentTenant.set(TenantId);
	}
	
	public static void clear() {
		currentTenant.remove();
	}

}

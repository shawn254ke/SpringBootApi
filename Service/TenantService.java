package app.Api.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.Api.Datasource.DataSourceManager;
import app.Api.Datasource.TenantContext;
import app.Api.DynamicEntity.ClazzEntity;
import app.Api.DynamicRepository.ClazzRepository;
import app.Api.Entity.School;
import app.Api.Entity.User;
import app.Api.Repository.SchoolRepository;
import app.Api.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantService {
	@Autowired
    private final DataSourceManager dataSourceManager;
	@Autowired 
    private final UserRepository userRepository;
	@Autowired
	private final SchemaService schemaService;
	@Autowired
	private final SchoolRepository schRepo;
	@Autowired
	private final ClazzRepository ClzRepo;
	

    

    public void addNewTenant(String tenantId) {
    	schemaService.createSchemaForUser(tenantId);
        dataSourceManager.addTenant(tenantId) ;
        TenantContext.setCurrentTenant(tenantId);
    }

    @Transactional
    public void processUser(String tenantId, User user) {
        TenantContext.setCurrentTenant(tenantId); // Switch to the tenant's data source.
        userRepository.save(user);
        TenantContext.clear();
    }
    @Transactional
    public void AddSchool(School school) {
    	schRepo.save(school);
    	
    }
    @Transactional(transactionManager = "dynamicTransactionManager")
    public void addClazz(ClazzEntity clazz, String SchamaID) {
    	TenantContext.setCurrentTenant(SchamaID);
    	ClzRepo.save(clazz);
//    	TenantContext.clear();
    	
    }
    @Transactional
    public void loadSchemaNames() {
    	List<School> schools = schRepo.findAll();
    	schools.forEach(sch->{
    		dataSourceManager.addTenant(sch.getSchemaName());
    		System.out.println(sch.getSchemaName());
    	});
    	dataSourceManager.getDynamicDatasource().getResolvedDataSources().forEach((key,value)->{
    		System.out.println(key+" "+value);
    	});
    	
    }
   
}

package app.Api.Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StartupLoader implements CommandLineRunner {

    private final TenantService tenantService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading schema names at startup...");
        tenantService.loadSchemaNames();
    }
}

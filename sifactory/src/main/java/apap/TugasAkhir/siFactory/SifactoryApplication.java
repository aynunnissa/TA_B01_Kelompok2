package apap.TugasAkhir.siFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(servers = {
		@Server(url = "/", description = "Default Server URL")
})

@SpringBootApplication
public class SifactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SifactoryApplication.class, args);
	}

}

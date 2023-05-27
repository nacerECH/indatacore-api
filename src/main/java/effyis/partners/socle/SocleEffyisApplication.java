package effyis.partners.socle;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

import effyis.partners.socle.entity.MyObject;
import effyis.partners.socle.repository.MyObjectRepository;
import effyis.partners.socle.repository.RoleRepository;
import effyis.partners.socle.utils.CSVHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import effyis.partners.socle.repository.AccountRepository;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 *
 * @author ECHAFAI Nassreddine
 *
 */
@SpringBootApplication
@EnableWebSecurity
@EnableAsync
public class SocleEffyisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SocleEffyisApplication.class, args);
	}

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {



	}

	@Bean
	CommandLineRunner start (MyObjectRepository myObjectRepository){

		return  args -> {
			try {
				InputStream inputStream = getClass().getClassLoader().getResourceAsStream("objectsRow.csv");
				List<MyObject> myObjectList = CSVHelper.csvToObjects(inputStream);
				myObjectList.stream().map(obj-> myObjectRepository.save(obj));
			} catch (Exception e) {
				throw new RuntimeException("Error: " + e.getMessage());
			}
		};

	}

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("Bearer Token",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
				.info(new Info().title("SOCLE API")
						.description("Socle technique pour l'utiliser dans tout les projet effyis").version("1.0.0"));
	}


	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean(name = "messageSource")
	public ResourceBundleMessageSource bundleMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Bean
	ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}


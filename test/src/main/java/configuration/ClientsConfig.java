package configuration;

import clients.UserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(ApplicationProperties.class)
public class ClientsConfig {

    @Bean
    public UserClient userClient(RestTemplate restTemplate, ApplicationProperties applicationProperties) {
        return new UserClient(restTemplate, applicationProperties);
    }
}

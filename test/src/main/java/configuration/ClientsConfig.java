package configuration;

import clients.UserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestOperations;

@Configuration
@Import(ApplicationProperties.class)
public class ClientsConfig {

    @Bean
    public UserClient userClient(RestOperations restOperations) {
        return new UserClient(restOperations);
    }
}

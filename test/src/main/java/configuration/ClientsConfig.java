package configuration;

import clients.UserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientsConfig {

    @Bean
    public UserClient userClient(RestTemplate restTemplate) {
        return new UserClient(restTemplate);
    }
}

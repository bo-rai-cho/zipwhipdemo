package configuration;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplateBuilder().rootUri("https://api.zipwhip.com").build();
        restTemplate.getMessageConverters().removeIf(m -> m.getClass().isAssignableFrom(MappingJackson2HttpMessageConverter.class));
        restTemplate.getMessageConverters().add(messageConverter());
        return restTemplate;
    }

    private MappingJackson2HttpMessageConverter messageConverter() {

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setPrettyPrint(true);
        messageConverter.setObjectMapper(objectMapper());
        return messageConverter;
    }

    private ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        return objectMapper;
    }
}

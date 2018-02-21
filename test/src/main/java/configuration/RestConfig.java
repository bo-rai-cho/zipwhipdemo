package configuration;

import clients.interceptors.ClientCustomInterceptor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplateBuilder()
                .messageConverters(messageConverter())
                .interceptors(new ClientCustomInterceptor()) // Custom client interceptor for logging and custom headers
                .errorHandler(errorHandler()) // We need to set empty error handler to avoid any errors in our tests
                .build();
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

    private ResponseErrorHandler errorHandler() {

        return new ResponseErrorHandler() {

            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

            }
        };
    }
}

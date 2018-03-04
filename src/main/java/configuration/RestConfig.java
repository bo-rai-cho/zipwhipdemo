package configuration;

import clients.handlers.ZipRestTestErrorHandler;
import clients.interceptors.ZipRestClientInterceptor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;


@Configuration
@Import(ApplicationProperties.class)
public class RestConfig {

    @Bean
    public RestTemplate restTemplate(ApplicationProperties applicationProperties,
                                     ClientHttpRequestInterceptor requestInterceptor,
                                     ResponseErrorHandler errorHandler,
                                     ClientHttpRequestFactory requestFactory,
                                     MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter,
                                     FormHttpMessageConverter formHttpMessageConverter) {

        return new RestTemplateBuilder()
                .rootUri(applicationProperties.getApiBaseUrl())
                .requestFactory(requestFactory)
                .messageConverters(mappingJackson2HttpMessageConverter, formHttpMessageConverter)
                .interceptors(requestInterceptor)
                .errorHandler(errorHandler)
                .build();
    }

    @Bean
    public BufferingClientHttpRequestFactory  bufferingClientHttpRequestFactory() {
        // Buffering client factory for log response
        return new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
    }

    @Bean
    public ZipRestClientInterceptor requestInterceptor() {
        return new ZipRestClientInterceptor();
    }

    @Bean
    public ZipRestTestErrorHandler zipRestTestErrorHandler(ObjectMapper objectMapper) {
        return new ZipRestTestErrorHandler(objectMapper);
    }

    @Bean
    public FormHttpMessageConverter formHttpMessageConverter() {
        return new FormHttpMessageConverter();
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setPrettyPrint(true);
        messageConverter.setObjectMapper(objectMapper);
        return messageConverter;
    }

    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        return objectMapper;
    }
}

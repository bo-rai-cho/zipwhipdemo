package configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class ApplicationProperties {

    @Value("${api.base.url:https://api.zipwhip.com/}")
    private String apiBaseUrl;
}

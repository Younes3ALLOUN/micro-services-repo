package ma.enset.billingservice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix ="token")
@Data
public class MyConsulConfig {
    private long accessTokenTimeOut;
    private long refreshTokenTimeOut;
}

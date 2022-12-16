package ma.enset.billingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController

public class ConsulConfigRestController {
    @Autowired
    private  MyVaultConfig myVaultConfig;
    @Autowired
    private MyConsulConfig myConsulConfig;
    //@Value("${token.accessTokenTimeOut}")
    //private Long accessTokenTimeOut;
    //@Value("${token.refreshTokenTimeOut}")
    //private Long refreshTokenTimeOut;

    @GetMapping("/myconfig")
    public Map<String,Object> myconfig(){
        return Map.of("consulConfig",myConsulConfig,"vaultConfig",myVaultConfig);
    }
}

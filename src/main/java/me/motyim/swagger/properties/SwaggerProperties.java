package me.motyim.swagger.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Data
@Component
@ConfigurationProperties(prefix = "spring.swagger")
public class SwaggerProperties {

    private boolean enable = true;
    private boolean redirect;

    @NestedConfigurationProperty
    private InfoProperties info;

    @NestedConfigurationProperty
    private Map<String, DocketProperty> dockets = new HashMap<>();


}

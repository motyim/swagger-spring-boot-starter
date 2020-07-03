package me.motyim.swagger.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;


@Data
@ConfigurationProperties(prefix = "spring.swagger")
public class SwaggerProperties {

    private boolean enable = true;

    @NestedConfigurationProperty
    private InfoProperties info;

    public SwaggerProperties() {
        this.info = new InfoProperties();
    }

}

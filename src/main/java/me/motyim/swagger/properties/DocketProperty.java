package me.motyim.swagger.properties;

import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
public class DocketProperty {
    @NestedConfigurationProperty
    private InfoProperties info;
    private String basePackage;
    private String path;

    public DocketProperty() {
        this.info = new InfoProperties();
        this.basePackage = "";
        this.path = "";
    }
}


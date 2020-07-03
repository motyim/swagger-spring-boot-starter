package me.motyim.swagger.properties;

import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import springfox.documentation.service.ApiInfo;

import java.util.ArrayList;
import java.util.function.Supplier;

import static springfox.documentation.service.ApiInfo.DEFAULT;

@Data
public class InfoProperties implements Supplier<ApiInfo> {

    private String title;
    private String description;
    private String version;
    private String termsOfServiceUrl;
    private String license;
    private String licenseUrl;

    @NestedConfigurationProperty
    private ContactProperty contact;


    public InfoProperties() {
        this.title = DEFAULT.getTitle();
        this.description = DEFAULT.getDescription();
        this.version = DEFAULT.getVersion();
        this.termsOfServiceUrl = DEFAULT.getTermsOfServiceUrl();
        this.license = DEFAULT.getLicense();
        this.licenseUrl = DEFAULT.getLicenseUrl();
        this.contact = new ContactProperty();
    }


    @Override
    public ApiInfo get() {
        return new ApiInfo(title, description, version, termsOfServiceUrl, contact.get(), license, licenseUrl, new ArrayList<>());
    }
}

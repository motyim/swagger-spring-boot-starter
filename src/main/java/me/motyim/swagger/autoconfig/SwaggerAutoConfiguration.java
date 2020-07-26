package me.motyim.swagger.autoconfig;

import com.google.common.collect.Lists;
import me.motyim.swagger.properties.DocketProperty;
import me.motyim.swagger.properties.InfoProperties;
import me.motyim.swagger.properties.SwaggerProperties;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.plugin.core.OrderAwarePluginRegistry;
import org.springframework.plugin.core.PluginRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.DocumentationPlugin;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Map;

@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(prefix = "spring.swagger", name = "enable", havingValue = "true", matchIfMissing = true)
public class SwaggerAutoConfiguration {

    private final SwaggerProperties properties;

    private final Logger log = LoggerFactory.getLogger(SwaggerAutoConfiguration.class);

    @Autowired
    public SwaggerAutoConfiguration(SwaggerProperties properties) {
        log.info("Swagger Starter Enabled : {}",properties.isEnable());
        this.properties = properties;
    }


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean
    @Primary
    @Qualifier("documentationPluginRegistry")
    public PluginRegistry<DocumentationPlugin, DocumentationType> swaggerDocumentationPluginRegistry(List<DocumentationPlugin> plugins) {

        InfoProperties appInfo = properties.getInfo();

        log.debug("app info : {}", appInfo);


        Map<String, DocketProperty> dockets = properties.getDockets();
        dockets.forEach((key, value) -> log.debug("Key : {} , value {}", key, value));


        properties.getDockets().forEach(
                (key, value) -> {
                    Docket build = new Docket(DocumentationType.SWAGGER_2)
                            .groupName(key)
                            .apiInfo(value.getInfo().get())
                            .select()
                            .apis(RequestHandlerSelectors.basePackage(value.getBasePackage()))
                            .paths(PathSelectors.ant(value.getPath()))
                            .build()
                            .securitySchemes(Lists.newArrayList(value.getApiKey().get()));
                    plugins.add(build);

                }
        );

        log.info("Register swagger-dockets");
        return OrderAwarePluginRegistry.create(plugins);
    }

}

package me.motyim.swagger.properties;

import lombok.Data;
import springfox.documentation.service.ApiKey;

import java.util.function.Supplier;

@Data
public class   ApiKeyProperty implements Supplier<ApiKey> {
    private String name;
    private String keyname;
    private String passAS;

    @Override
    public ApiKey get() {
        return new ApiKey(name, keyname, passAS);
    }
}

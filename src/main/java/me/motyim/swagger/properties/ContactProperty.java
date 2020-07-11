package me.motyim.swagger.properties;

import lombok.Data;
import springfox.documentation.service.Contact;

import java.util.function.Supplier;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

@Data
public class ContactProperty implements Supplier<Contact> {

    private String name;
    private String url;
    private String email;

    public ContactProperty() {
        this.name = DEFAULT_CONTACT.getName();
        this.url = DEFAULT_CONTACT.getUrl();
        this.email = DEFAULT_CONTACT.getEmail();
    }

    public Contact get() {
        return new Contact(name, url, email);
    }
}

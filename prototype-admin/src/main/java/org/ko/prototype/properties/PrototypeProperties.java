package org.ko.prototype.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "prototype")
public class PrototypeProperties {

    /**
     * 作者信息
     */
    private AuthorProperties author = new AuthorProperties();


    public AuthorProperties getAuthor() {
        return author;
    }

    public void setAuthor(AuthorProperties author) {
        this.author = author;
    }
}

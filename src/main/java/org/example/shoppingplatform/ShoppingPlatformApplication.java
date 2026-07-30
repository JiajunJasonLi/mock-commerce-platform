package org.example.shoppingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShoppingPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingPlatformApplication.class, args);
    }

}
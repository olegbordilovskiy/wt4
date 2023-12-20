package by.bsuir.mycoolstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "by.bsuir.mycoolstore.dao")
@EntityScan(basePackages = "by.bsuir.mycoolstore.entity")
public class MyCoolStoreSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyCoolStoreSpringBootApplication.class, args);
    }

}

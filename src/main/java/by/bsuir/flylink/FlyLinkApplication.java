package by.bsuir.flylink;

import by.bsuir.flylink.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class FlyLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlyLinkApplication.class, args);
    }
}

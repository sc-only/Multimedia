package vacation.work.multimedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:kaptcha.xml"})
@SpringBootApplication
public class MultimediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultimediaApplication.class, args);
    }

}

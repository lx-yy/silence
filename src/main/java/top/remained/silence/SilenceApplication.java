package top.remained.silence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
//swagger扫描
@EnableOpenApi
@EnableAspectJAutoProxy
public class SilenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SilenceApplication.class, args);
    }

}

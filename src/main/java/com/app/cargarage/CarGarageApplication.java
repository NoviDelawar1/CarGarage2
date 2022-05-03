package com.app.cargarage;

import com.app.cargarage.model.User;
import com.app.cargarage.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class CarGarageApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarGarageApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserServiceImpl userService, BCryptPasswordEncoder encoder) {
        return args -> {
            userService.insertUsersOnRuntime(User.builder()
                    .id(1)
                    .fullName("Idris Delawar")
                    .username("admin")
                    .password(encoder.encode("admin"))
                    .role("ROLE_ADMIN")
                    .build());

            userService.insertUsersOnRuntime(User.builder()
                    .id(2)
                    .fullName("Justin Bieber")
                    .username("cashier")
                    .password(encoder.encode("cashier"))
                    .role("ROLE_CASHIER")
                    .build());

            userService.insertUsersOnRuntime(User.builder()
                    .id(3)
                    .fullName("Madona")
                    .username("back")
                    .password(encoder.encode("back"))
                    .role("ROLE_BACKOFFICE")
                    .build());

            userService.insertUsersOnRuntime(User.builder()
                    .id(4)
                    .fullName("Emma Watson")
                    .username("mechanic")
                    .password(encoder.encode("mechanic"))
                    .role("ROLE_MECHANIC")
                    .build());

            userService.insertUsersOnRuntime(User.builder()
                    .id(5)
                    .fullName("Shane Watson")
                    .username("administrative")
                    .password(encoder.encode("administrative"))
                    .role("ROLE_ADMINISTRATIVE")
                    .build());
        };
    }
}

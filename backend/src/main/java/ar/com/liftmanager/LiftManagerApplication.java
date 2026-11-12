package ar.com.liftmanager;

import ar.com.liftmanager.model.AppUser;
import ar.com.liftmanager.model.Role;
import ar.com.liftmanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LiftManagerApplication {
    public static void main(String[] args) { SpringApplication.run(LiftManagerApplication.class, args); }

    @Bean
    CommandLineRunner bootstrapAdmin(UserRepository users, PasswordEncoder encoder) {
        return args -> {
            String email = System.getenv("APP_BOOTSTRAP_ADMIN_EMAIL");
            String password = System.getenv("APP_BOOTSTRAP_ADMIN_PASSWORD");
            if (email != null && !email.isBlank() && password != null && password.length() >= 12
                    && users.findByEmailIgnoreCase(email).isEmpty()) {
                AppUser admin = new AppUser();
                admin.setName("Administrador principal");
                admin.setEmail(email.trim().toLowerCase());
                admin.setPasswordHash(encoder.encode(password));
                admin.setRole(Role.ADMIN);
                admin.setActive(true);
                users.save(admin);
            }
        };
    }
}

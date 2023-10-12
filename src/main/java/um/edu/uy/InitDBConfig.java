package um.edu.uy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import um.edu.uy.business.entities.User;
import um.edu.uy.persistence.UserRepository;

@Configuration
public class InitDBConfig {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepo) {

        return args -> {
            User u = User.builder()
                    .name("Usuario1")
                    .address("Calle Artigas 1485")
                    .document(1658465L)
                    .build();
            userRepo.save(u);
            u = User.builder()
                    .name("Usuario2")
                    .address("Calle Lavalleja 8541")
                    .document(1658465L)
                    .build();
            userRepo.save(u);
        };
    }
}

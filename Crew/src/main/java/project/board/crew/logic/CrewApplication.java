package project.board.crew.logic;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class CrewApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrewApplication.class, args);
    }
}

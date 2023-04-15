package project.board.crew.logic;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class Configuration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

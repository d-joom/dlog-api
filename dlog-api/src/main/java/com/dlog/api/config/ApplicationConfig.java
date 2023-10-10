package com.dlog.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        /* modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); */
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }
}

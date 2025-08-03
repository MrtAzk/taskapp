package com.mert.taskmanagement.taskapp.core.config.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperManager {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}

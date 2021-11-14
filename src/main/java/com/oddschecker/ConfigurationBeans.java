package com.oddschecker;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
Adding here shared common beans
 */
@Configuration
public class ConfigurationBeans {

    @Bean
    public ObjectMapper objectMapper() { return  new ObjectMapper();}

}

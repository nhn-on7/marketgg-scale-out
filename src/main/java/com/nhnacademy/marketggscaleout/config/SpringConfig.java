package com.nhnacademy.marketggscaleout.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.marketggscaleout.service.StorageService;
import com.nhnacademy.marketggscaleout.service.impl.CloudStorageService;
import com.nhnacademy.marketggscaleout.service.impl.LocalStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class SpringConfig {

    @Bean
    public StorageService localStorageService() {
        return new LocalStorageService();
    }

    @Bean
    public StorageService cloudStorageService() {
        return new CloudStorageService(new RestTemplate(), new ObjectMapper());
    }

}

package com.nhnacademy.marketggscaleout.config;

import com.nhnacademy.marketggscaleout.controller.StorageController;
import com.nhnacademy.marketggscaleout.repository.ImageRepository;
import com.nhnacademy.marketggscaleout.repository.impl.DefaultImageRepository;
import com.nhnacademy.marketggscaleout.service.StorageService;
import com.nhnacademy.marketggscaleout.service.impl.CloudStorageService;
import com.nhnacademy.marketggscaleout.service.impl.LocalStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SpringConfig {

    @Value(("${storage.option}"))
    private String option;

    @Bean
    public StorageController storageController() {
        if (option.equals("local")) {
            return new StorageController(localStorageService());
        }
        return new StorageController(cloudStorageService());
    }

    @Bean
    public StorageService cloudStorageService() {
        return new CloudStorageService(imageRepository());
    }

    @Bean
    public StorageService localStorageService() {
        return new LocalStorageService(imageRepository());
    }

    @Bean
    public ImageRepository imageRepository() {
        return new DefaultImageRepository();
    }

}

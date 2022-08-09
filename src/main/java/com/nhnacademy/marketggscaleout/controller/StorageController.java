package com.nhnacademy.marketggscaleout.controller;

import com.nhnacademy.marketggscaleout.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

}
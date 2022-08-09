package com.nhnacademy.marketggscaleout.controller;

import com.nhnacademy.marketggscaleout.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

}

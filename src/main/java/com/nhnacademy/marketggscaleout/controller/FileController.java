package com.nhnacademy.marketggscaleout.controller;

import com.nhnacademy.marketggscaleout.service.FileService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @PostMapping
    public ResponseEntity<Void> uploadFile(@RequestPart final MultipartFile image) throws IOException {
        fileService.uploadFile(image);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .contentType(MediaType.APPLICATION_JSON)
                             .build();
    }

}

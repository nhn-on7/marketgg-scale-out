package com.nhnacademy.marketggscaleout.service.impl;

import com.nhnacademy.marketggscaleout.repository.ImageRepository;
import com.nhnacademy.marketggscaleout.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class CloudStorageService implements StorageService {

    private final ImageRepository imageRepository;

    @Override
    public void uploadImage(MultipartFile image) {

    }
}

package com.nhnacademy.marketggscaleout.service.impl;

import com.nhnacademy.marketggscaleout.entity.Image;
import com.nhnacademy.marketggscaleout.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
public class CloudStorageService implements StorageService {

    @Override
    public Image uploadImage(final MultipartFile image) {

        return null;
    }

}

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

    // TODO 4: API 설명서를 보며 Token 요청을 구현하세요
    public String requestToken() {
        return null;
    }

}

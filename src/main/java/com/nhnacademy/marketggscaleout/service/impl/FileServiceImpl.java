package com.nhnacademy.marketggscaleout.service.impl;

import com.nhnacademy.marketggscaleout.entity.Image;
import com.nhnacademy.marketggscaleout.repository.ImageRepository;
import com.nhnacademy.marketggscaleout.service.FileService;
import com.nhnacademy.marketggscaleout.service.StorageService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    @Value(("${storage.option}"))
    private String option;

    private final List<StorageService> storageServices;
    private final ImageRepository imageRepository;

    public void uploadFile(final MultipartFile image) throws IOException {

        if (option.equals("local")) {
            Image imageEntity = storageServices.get(0).uploadImage(image);
            imageRepository.save(imageEntity);
        } else {
            storageServices.get(1).uploadImage(image);
        }
    }

}

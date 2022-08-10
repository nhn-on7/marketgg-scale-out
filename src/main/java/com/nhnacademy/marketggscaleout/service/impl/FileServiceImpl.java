package com.nhnacademy.marketggscaleout.service.impl;

import com.nhnacademy.marketggscaleout.entity.Image;
import com.nhnacademy.marketggscaleout.exception.ImageNotFoundException;
import com.nhnacademy.marketggscaleout.factory.StorageServiceFactory;
import com.nhnacademy.marketggscaleout.repository.ImageRepository;
import com.nhnacademy.marketggscaleout.service.FileService;
import java.io.IOException;
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

    private final StorageServiceFactory storageServiceFactory;
    private final ImageRepository imageRepository;

    public void uploadFile(final MultipartFile image) throws IOException {
        Image imageEntity = storageServiceFactory.getService(option).uploadImage(image);
        imageRepository.save(imageEntity);
    }

    @Override
    public void downloadFile(final Long id) throws IOException {
        Image image = imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
        storageServiceFactory.getService(option).downloadImage(image);
    }

}

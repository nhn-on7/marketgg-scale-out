package com.nhnacademy.marketggscaleout.service.impl;

import com.nhnacademy.marketggscaleout.entity.Image;
import com.nhnacademy.marketggscaleout.service.StorageService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
public class LocalStorageService implements StorageService {

    private static final String DIR = System.getProperty("user.home");

    @Override
    public Image uploadImage(final MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            throw new IllegalArgumentException("이미지가 없습니다");
        }

        String dir = String.valueOf(Files.createDirectories(returnDir()));
        String filename = UUID.randomUUID() + ".png";

        File dest = new File(dir, filename);
        image.transferTo(dest);

        return Image.builder().imageAddress(dir).name(filename).build();
    }

    @Override
    public void downloadImage(Image image) throws IOException {
        File localFile = new File(image.getImageAddress(), image.getName());
        File downloadFile = new File(image.getImageAddress(), UUID.randomUUID() + ".png");
        FileUtils.copyFile(localFile, downloadFile);
    }

    private Path returnDir() {
        String format = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        return Paths.get(DIR, format);
    }
}

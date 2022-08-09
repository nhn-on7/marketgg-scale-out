package com.nhnacademy.marketggscaleout.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    //TODO 2: 로컬 업로드 기능을 구현하세요
    void uploadFile(final MultipartFile image);

}

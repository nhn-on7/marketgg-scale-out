package com.nhnacademy.marketggscaleout.service;

import org.springframework.web.multipart.MultipartFile;

// TODO 1: application 구동시 local, cloud 둘 중 한 가지를 선택해서 동작하도록 설계하시오
public interface StorageService {

    //TODO 2: 로컬 업로드 기능을 구현하세요.
    void uploadImage(MultipartFile image);

}
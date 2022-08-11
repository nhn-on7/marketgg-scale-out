package com.nhnacademy.marketggscaleout.service;

import com.nhnacademy.marketggscaleout.dto.request.ImageRequest;
import com.nhnacademy.marketggscaleout.dto.response.ImageResponse;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

// TODO 1: application 구동시 local, cloud 둘 중 한 가지를 선택해서 동작하도록 설계하시오
public interface StorageService {

    ImageRequest uploadImage(MultipartFile image) throws IOException;

    // TODO 6: 로컬 다운로드 기능을 구현하세요
    // TODO 7: cloud 다운로드 기능을 구현하세요
    void downloadImage(final ImageResponse image) throws IOException;
}

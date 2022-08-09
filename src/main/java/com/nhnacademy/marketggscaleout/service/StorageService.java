package com.nhnacademy.marketggscaleout.service;

import com.nhnacademy.marketggscaleout.entity.Image;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

// TODO 1: application 구동시 local, cloud 둘 중 한 가지를 선택해서 동작하도록 설계하시오
public interface StorageService {

    Image uploadImage(MultipartFile image) throws IOException;

}

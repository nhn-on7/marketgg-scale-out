package com.nhnacademy.marketggscaleout.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.marketggscaleout.dto.request.AccessRequest;
import com.nhnacademy.marketggscaleout.dto.request.Auth;
import com.nhnacademy.marketggscaleout.dto.request.PasswordCredentials;
import com.nhnacademy.marketggscaleout.dto.response.AccessResponse;
import com.nhnacademy.marketggscaleout.entity.Image;
import com.nhnacademy.marketggscaleout.repository.ImageRepository;
import com.nhnacademy.marketggscaleout.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
public class CloudStorageService implements StorageService {

    private final ImageRepository imageRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private static final String HEADER_NAME = "X-Auth-Token";
    private PasswordCredentials passwordCredentials;
    private Auth auth;
    private AccessRequest accessRequest;
    private AccessResponse accessResponse;

    @Value("${cloud.auth-url}")
    private String authUrl;
    @Value("${cloud.user-name}")
    private String userName;
    @Value("${cloud.password}")
    private String password;
    @Value("${cloud.tenant-id}")
    private String tenantId;
    @Value("${cloud.storage-url}")
    private String storageUrl;

    // TODO 4: API 설명서를 보며 Token 요청을 구현하세요
    public String requestToken() {
        passwordCredentials = new PasswordCredentials(userName, password);
        auth = new Auth(tenantId, passwordCredentials);
        accessRequest = new AccessRequest(auth);

        String identityUrl = this.authUrl + "/tokens";

        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<AccessRequest> httpEntity = new HttpEntity<>(this.accessRequest, headers);

        // 토큰 요청
        ResponseEntity<String> response =
            this.restTemplate.exchange(identityUrl, HttpMethod.POST, httpEntity, String.class);

        return response.getBody();
    }

    // TODO 5: API 설명서를 보며 Object Storage에 이미지를 업로드하세요
    @Override
    public Image uploadImage(final MultipartFile image) {

        return null;
    }


}

package com.nhnacademy.marketggscaleout.service.impl;

import static org.springframework.http.HttpMethod.PUT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.marketggscaleout.dto.request.AccessRequest;
import com.nhnacademy.marketggscaleout.dto.request.Auth;
import com.nhnacademy.marketggscaleout.dto.request.PasswordCredentials;
import com.nhnacademy.marketggscaleout.dto.response.AccessResponse;
import com.nhnacademy.marketggscaleout.entity.Image;
import com.nhnacademy.marketggscaleout.service.StorageService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
public class CloudStorageService implements StorageService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

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

    private static final String HEADER_NAME = "X-Auth-Token";
    private static final String DIR = System.getProperty("java.io.tmpdir");

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
    public Image uploadImage(final MultipartFile image) throws IOException {
        String filename = UUID.randomUUID() + ".png";
        File objFile = new File(DIR, Objects.requireNonNull(filename));
        String url = this.getUrl(filename);
        image.transferTo(objFile);
        RequestCallback requestCallback;

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setBufferRequestBody(false);
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        HttpMessageConverterExtractor<String> responseExtractor =
            new HttpMessageConverterExtractor<>(String.class, restTemplate.getMessageConverters());

        try (InputStream inputStream = new FileInputStream(objFile)) {
            accessResponse = objectMapper.readValue(requestToken(), AccessResponse.class);
            String tokenId = accessResponse.getAccess().getToken().getId();

            requestCallback = request -> {
                request.getHeaders().add(HEADER_NAME, tokenId);
                IOUtils.copy(inputStream, request.getBody());
            };

            restTemplate.execute(url, PUT, requestCallback, responseExtractor);
            log.info("업로드 성공");

        } catch (IOException e) {
            log.error("error: {}", e.getMessage());
            throw e;
        }

        return Image.builder()
                    .name(filename)
                    .imageAddress(url)
                    .build();
    }

    @Override
    public void downloadImage(Image image) throws IOException {

    }

    private String getUrl(String objectName) {
        return this.storageUrl + "/on7_storage/" + objectName;
    }

}

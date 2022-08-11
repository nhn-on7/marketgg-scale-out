package com.nhnacademy.marketggscaleout.factory;

import com.nhnacademy.marketggscaleout.service.StorageService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// TODO 8: local/ storage 기능 구분을 팩토리 패턴을 적용하여 리팩토링 하세요
@Component
@RequiredArgsConstructor
public class StorageServiceFactory {
    private final Map<String, StorageService> storageServiceMap;

    public StorageService getService(String name) {
        return storageServiceMap.get(name);
    }

}

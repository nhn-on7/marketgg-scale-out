package com.nhnacademy.marketggscaleout.factory;

import com.nhnacademy.marketggscaleout.service.StorageService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

// TODO 8: local/ storage 기능 구분을 팩토리 패턴을 적용하여 리팩토링 하세요
@Component
public class StorageServiceFactory {

    private final Map<String, StorageService> storageServices = new HashMap<>();

    public StorageServiceFactory(List<StorageService> storageServices) {
        if(CollectionUtils.isEmpty(storageServices)) {
            throw new IllegalArgumentException("StorageService가 없습니다");
        }

        for (StorageService storageService : storageServices) {
            this.storageServices.put(storageService.getClass().getSimpleName(), storageService);
        }
    }

    public StorageService getService(String name) {
        return storageServices.get(name);
    }
}

package com.nhnacademy.marketggscaleout.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ImageRequest {

    private String name;

    private String imageAddress;

    @Builder
    private ImageRequest(String name, String imageAddress) {
        this.name = name;
        this.imageAddress = imageAddress;
    }

}

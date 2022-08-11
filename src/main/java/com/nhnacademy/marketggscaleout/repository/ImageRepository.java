package com.nhnacademy.marketggscaleout.repository;

import com.nhnacademy.marketggscaleout.dto.response.ImageResponse;
import com.nhnacademy.marketggscaleout.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long>{

    ImageResponse queryById(Long id);
}

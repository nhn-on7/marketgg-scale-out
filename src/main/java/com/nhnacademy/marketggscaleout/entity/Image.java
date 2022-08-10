package com.nhnacademy.marketggscaleout.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "images")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_no")
    private Long id;

    @Column
    private String name;

    @Column(name = "image_address")
    private String imageAddress;

    @Builder
    private Image(String name, String imageAddress) {
        this.name = name;
        this.imageAddress = imageAddress;
    }

}

package com.unifarm.server.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "program")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int programIdx;

    private String title;
    private String body;

    private String thumbnail;
    private String address;
    private String target;

    private int regiNumber;
    private String maxNumber;
    private int viewCount;
    private String cost;

    private String ask;
    private String reward;

}

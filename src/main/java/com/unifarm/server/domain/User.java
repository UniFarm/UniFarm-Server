package com.unifarm.server.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userIdx;

    private String password;
    private String email;
    private String photoUrl;

    /*
    DB 설계 후 추가
     */
}
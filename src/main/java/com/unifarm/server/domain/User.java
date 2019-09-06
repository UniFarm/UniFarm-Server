package com.unifarm.server.domain;

import lombok.Data;

import javax.persistence.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userIdx;
    private String password;
    private String email;
    private String name;
    private String entranceYear;
    private String major;
    private String gender;
}
package com.unifarm.server.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "keyword")
public class Keyword{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int keywordIdx;

    @Column(name="info", nullable = false)
    private String info;

}

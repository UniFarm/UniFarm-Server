package com.unifarm.server.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "keyword_program")
public class KeywordProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int keywordProgramIdx;

    private int keywordIdx;
    private int programIdx;

}

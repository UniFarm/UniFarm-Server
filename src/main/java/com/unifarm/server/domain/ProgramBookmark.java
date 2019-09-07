package com.unifarm.server.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "program_bookmark")
public class ProgramBookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userIdx;

    private int programIdx;
}

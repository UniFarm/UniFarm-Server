package com.unifarm.server.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "program_user")
public class ProgramUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int programUserIdx;

    private int programIdx;
    private int userIdx;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDate startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDate endDate;
}

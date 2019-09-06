package com.unifarm.server.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "program_date")
public class ProgramDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int programDateIdx;

    private int programIdx;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDate startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDate endDate;

}

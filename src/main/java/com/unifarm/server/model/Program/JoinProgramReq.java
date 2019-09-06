package com.unifarm.server.model.Program;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JoinProgramReq {

    private int programIdx;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDate startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDate endDate;

}

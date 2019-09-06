package com.unifarm.server.model.Program;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class JoinProgramRes {

    List<Date> programIng;
    List<Date> programEnd;

}

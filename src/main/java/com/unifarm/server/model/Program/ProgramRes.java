package com.unifarm.server.model.Program;

import com.unifarm.server.domain.Keyword;
import com.unifarm.server.domain.KeywordProgram;
import com.unifarm.server.domain.Program;
import com.unifarm.server.domain.ProgramDate;
import lombok.Data;

import java.util.List;

@Data
public class ProgramRes {

    private Program program;
    private List<ProgramDate> programDates;
    private List<Keyword> keywords;

}

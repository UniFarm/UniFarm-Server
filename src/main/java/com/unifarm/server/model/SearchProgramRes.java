package com.unifarm.server.model;

import com.unifarm.server.domain.Keyword;
import com.unifarm.server.domain.KeywordProgram;
import com.unifarm.server.domain.Program;
import lombok.Data;

import java.util.List;

@Data
public class SearchProgramRes {
    private Program program;
    private List<Keyword> keywordProgram;
}

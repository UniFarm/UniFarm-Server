package com.unifarm.server.service;

import com.unifarm.server.domain.Keyword;
import com.unifarm.server.domain.KeywordProgram;
import com.unifarm.server.domain.Program;
import com.unifarm.server.domain.User;
import com.unifarm.server.model.DefaultRes;
import com.unifarm.server.model.SearchProgramRes;
import com.unifarm.server.respository.*;
import com.unifarm.server.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SearchService {
    private final UserRepository userRepository;
    private final KeywordRepository keywordRepository;
    private final KeywordUserRepository keywordUserRepository;
    private final ProgramRepository programRepository;
    private final KeywordProgramRepository keywordProgramRepository;
    private final JwtService jwtService;

    public SearchService(final UserRepository userRepository,
                         final KeywordRepository keywordRepository,
                         final KeywordUserRepository keywordUserRepository,
                         final ProgramRepository programRepository,
                         final KeywordProgramRepository keywordProgramRepository,
                       final JwtService jwtService) {
        this.userRepository = userRepository;
        this.keywordRepository = keywordRepository;
        this.keywordUserRepository = keywordUserRepository;
        this.programRepository = programRepository;
        this.keywordProgramRepository = keywordProgramRepository;
        this.jwtService = jwtService;
    }

    /**
     * userIdx로 회원 조회
     * @param keyword
     * @return
     */
    public Program findPrograms(final String keyword) {
        Optional<List<Keyword>> keywords = keywordRepository.findByInfoContaining(keyword);
        return keywords.map(value ->
                        programRepository.findByProgramIdx(
                                keywordProgramRepository.findByKeywordIdx(value.iterator().next().getKeywordIdx()).map(v->v.getProgramIdx()).get()).get())
                .orElseGet(null);
    }


    /**
     * userIdx로 회원 조회
     * @param programIdx
     * @return
     */


    public List<Keyword> findKeywordsByProgramId(final int programIdx) {
        Optional<Program> program = programRepository.findByProgramIdx(programIdx);
        Optional<List<KeywordProgram>> keywordProgram = keywordProgramRepository.findByProgramIdx(programIdx);
        List<Keyword> keywords = new ArrayList<>();
        for(int i = 0; i < keywordProgram.get().size(); i++)
        {
            int keywordIdx = keywordProgram.get().get(i).getKeywordIdx();
            Optional<Keyword> keyword = keywordRepository.findByKeywordIdx(keywordIdx);
            keywords.add(keyword.get());
        }
        if(keywords.isEmpty()) return null;
        else return keywords;
    }
}

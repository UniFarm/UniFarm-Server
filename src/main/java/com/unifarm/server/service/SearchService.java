package com.unifarm.server.service;

import com.unifarm.server.domain.*;
import com.unifarm.server.model.DefaultRes;
import com.unifarm.server.model.Program.ProgramRes;
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
    private final KeywordSearchRepository keywordSearchRepository;
    private final JwtService jwtService;
    private final ProgramService programService;

    public SearchService(final UserRepository userRepository,
                         final KeywordRepository keywordRepository,
                         final KeywordUserRepository keywordUserRepository,
                         final ProgramRepository programRepository,
                         final KeywordSearchRepository keywordSearchRepository,
                         final KeywordProgramRepository keywordProgramRepository,
                         final ProgramService programService,
                         final JwtService jwtService) {
        this.userRepository = userRepository;
        this.keywordRepository = keywordRepository;
        this.keywordUserRepository = keywordUserRepository;
        this.programRepository = programRepository;
        this.keywordSearchRepository = keywordSearchRepository;
        this.keywordProgramRepository = keywordProgramRepository;
        this.jwtService = jwtService;
        this.programService = programService;
    }

    /**
     * 프로그램 조회
     * @param keyword
     * @return
     */
    public List<SearchProgramRes> findPrograms(final String keyword) {
        List<Keyword> keywords = keywordRepository.findAllByInfoContaining(keyword).get();

        List<SearchProgramRes> searchProgramResList = new ArrayList<>();
        for (Keyword k : keywords) {
            List<Optional<KeywordProgram>> keywordPrograms = keywordProgramRepository.findAllByKeywordIdx(k.getKeywordIdx());
            for (Optional<KeywordProgram> kp : keywordPrograms) {
                SearchProgramRes searchProgramRes = new SearchProgramRes();
                int programIdx = kp.get().getProgramIdx();
                Optional<Program> program = programRepository.findByProgramIdx(programIdx);
                Optional<List<KeywordProgram>> keywordProgram = keywordProgramRepository.findByProgramIdx(programIdx);
                List<Keyword> keywordsList = new ArrayList<>();

                for (int i = 0; i < keywordProgram.get().size(); i++) {
                    int keywordIdx = keywordProgram.get().get(i).getKeywordIdx();
                    Optional<Keyword> keywordOpt = keywordRepository.findByKeywordIdx(keywordIdx);

                    keywordsList.add(keywordOpt.get());
                }

                ProgramRes data = new ProgramRes();

                data.setProgram(program.get());

                data.setKeywords(keywordsList);

                searchProgramRes.setProgram(data.getProgram());
                searchProgramRes.setKeywordPrograms(data.getKeywords());
                searchProgramResList.add(searchProgramRes);
            }
        }
        return searchProgramResList;
    }
}
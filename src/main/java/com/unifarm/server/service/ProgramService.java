package com.unifarm.server.service;

import com.unifarm.server.domain.*;
import com.unifarm.server.model.DefaultRes;
import com.unifarm.server.model.Program.*;
import com.unifarm.server.respository.*;
import com.unifarm.server.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;

@Slf4j
@Service
public class ProgramService {

    private final KeywordUserRepository keywordUserRepository;
    private final UserRepository userRepository;
    private final KeywordRepository keywordRepository;
    private final ProgramRepository programRepository;
    private final ProgramDateRepository programDateRepository;
    private final KeywordProgramRepository keywordProgramRepository;
    private final ProgramUserRepository programUserRepository;
    private final JwtService jwtService;

    public ProgramService(final ProgramRepository programRepository,
                          final KeywordUserRepository keywordUserRepository,
                          final JwtService jwtService,
                          final UserRepository userRepository,
                          final KeywordRepository keywordRepository,
                          final KeywordProgramRepository keywordProgramRepository,
                          final ProgramDateRepository programDateRepository,
                          final ProgramUserRepository programUserRepository) {

        this.programRepository = programRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.keywordUserRepository = keywordUserRepository;
        this.keywordRepository = keywordRepository;
        this.keywordProgramRepository = keywordProgramRepository;
        this.programUserRepository = programUserRepository;
        this.programDateRepository = programDateRepository;

    }


    /**
     * 신청 프로그램 조회
     * @param userIdx
     * @return
     */
    public DefaultRes getJoinPrograms(final int userIdx){

        try {
            Optional<List<ProgramUser>> programUsers = programUserRepository.findByUserIdx(userIdx);
            List<Optional<Program>> programs = new ArrayList<>();
            JoinProgramRes data = new JoinProgramRes();

            for (int i = 0; i < programUsers.get().size(); i++) {
                programs.add(programRepository.findById(programUsers.get().get(i).getProgramIdx()));
            }

            List<Date> ing = new ArrayList<>();
            List<Date> end = new ArrayList<>();

            for (int i = 0; i < programs.size(); i++) {
                Date res = new Date();
                LocalDate startDate = programUsers.get().get(i).getStartDate();
                LocalDate endDate = programUsers.get().get(i).getEndDate();
                LocalDate today = LocalDate.now();

                res.setTitle(programs.get(i).get().getTitle());
                res.setStartDate(startDate);
                res.setEndDate(endDate);

                if(today.until(startDate).getMonths() == 0) res.setDDay(today.until(startDate).getDays());
                else res.setDDay(-1);

                if(!endDate.isBefore(today)) ing.add(res);
                else end.add(res);
            }

            data.setProgramIng(ing);
            data.setProgramEnd(end);

            return DefaultRes.res(StatusCode.OK, "조회 성공", data);
        }
        catch (Exception e)
        {
            log.info(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, "데이터베이스 에러");
        }
    }

    /**
     * 프로그램 상세 조회
     * @param programIdx
     * @return
     */
    public DefaultRes findProgram(final int programIdx)
    {
        try{
            Optional<Program> program = programRepository.findByProgramIdx(programIdx);
            Optional<List<ProgramDate>> programDate = programDateRepository.findByProgramIdx(programIdx);
            Optional<List<KeywordProgram>> keywordProgram = keywordProgramRepository.findByProgramIdx(programIdx);
            List<Keyword> keywords = new ArrayList<>();

            for(int i = 0; i < keywordProgram.get().size(); i++)
            {
                int keywordIdx = keywordProgram.get().get(i).getKeywordIdx();
                Optional<Keyword> keyword = keywordRepository.findByKeywordIdx(keywordIdx);

                keywords.add(keyword.get());
            }

            ProgramRes data = new ProgramRes();
            data.setProgram(program.get());
            data.setProgramDates(programDate.get());

            data.setKeywords(keywords);

            programRepository.updateViewCount(programIdx);

            return DefaultRes.res(StatusCode.OK, "조회 성공", data);

        } catch (Exception e)
        {
            log.info(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, "데이터베이스 에러");
        }
    }

    /**
     * 전공기준 프로그램 조회
     * @param userIdx
     * @return
     */
    public DefaultRes findByMajor(final int userIdx)
    {
        try{
            Optional<User> user = userRepository.findByUserIdx(userIdx);
            Optional<List<Keyword>> keywords = keywordRepository.findByInfo(user.get().getMajor());
            List<ProgramRes> datas = new ArrayList<>();

            for(int i = 0; i < keywords.get().size(); i++)
            {
                Optional<KeywordProgram> keywordProgram = keywordProgramRepository.findByKeywordIdx(keywords.get().get(i).getKeywordIdx());
                Optional<Program> program = programRepository.findByProgramIdx(keywordProgram.get().getProgramIdx());


                Optional<List<ProgramDate>> programDate = programDateRepository.findByProgramIdx(program.get().getProgramIdx());
                Optional<List<KeywordProgram>> keywordPrograms = keywordProgramRepository.findByProgramIdx(program.get().getProgramIdx());
                List<Keyword> keywords2 = new ArrayList<>();

                for(int j = 0; j < keywordPrograms.get().size(); j++)
                {
                    int keywordIdx = keywordPrograms.get().get(j).getKeywordIdx();
                    Optional<Keyword> keyword = keywordRepository.findByKeywordIdx(keywordIdx);

                    keywords2.add(keyword.get());
                }

                ProgramRes data = new ProgramRes();
                data.setProgram(program.get());
                data.setProgramDates(programDate.get());
                data.setKeywords(keywords2);

                datas.add(data);

            }

            return DefaultRes.res(StatusCode.OK, "조회 성공", datas);
        } catch (Exception e)
        {
            log.info(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, "맞춤 정보 없음");
        }
    }

    /**
     * 관심 키워드 기준 프로그램 조회
     * @param userIdx
     * @param
     */
    public DefaultRes findByKeyword(final int userIdx)
    {
        try{
            Optional<User> user = userRepository.findByUserIdx(userIdx);
            Optional<List<KeywordUser>> keywordUsers = keywordUserRepository.findByUserIdx(user.get().getUserIdx());
            List<ProgramRes> datas = new ArrayList<>();

            if(!keywordUsers.get().isEmpty()) {
                for (int i = 0; i < keywordUsers.get().size(); i++) {
                    Keyword keyword = keywordRepository.findByKeywordIdx(keywordUsers.get().get(i).getKeywordIdx()).get();
                    if(keywordProgramRepository.countByKeywordIdx(keyword.getKeywordIdx()) > 0) {
                        KeywordProgram programKeyword = keywordProgramRepository.findByKeywordIdx(keyword.getKeywordIdx()).get();
                        Program program = programRepository.findByProgramIdx(programKeyword.getProgramIdx()).get();
                        List<Keyword> keywords = new ArrayList<>();
                        Optional<List<ProgramDate>> programDate = programDateRepository.findByProgramIdx(programKeyword.getProgramIdx());
                        Optional<List<KeywordProgram>> keywordPrograms = keywordProgramRepository.findByProgramIdx(programKeyword.getProgramIdx());

                        for (int j = 0; j < keywordPrograms.get().size(); j++) {
                            int keywordIdx = keywordPrograms.get().get(j).getKeywordIdx();
                            Optional<Keyword> keyword2 = keywordRepository.findByKeywordIdx(keywordIdx);

                            keywords.add(keyword2.get());
                        }

                        ProgramRes data = new ProgramRes();
                        data.setProgram(program);
                        data.setProgramDates(programDate.get());
                        data.setKeywords(keywords);

                        datas.add(data);
                    }
                }
            }

            return DefaultRes.res(StatusCode.OK, "조회 성공", datas);
        } catch (Exception e)
        {
            log.info(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, "데이터베이스 에러");
        }
    }

    /**
     * 실시간 인기 프로그램 조회
     * @param
     * @return
     */
    public DefaultRes findPopular(){
        try{
            Sort sort = new Sort(Sort.Direction.ASC, "viewCount");
            List<Program> programs = programRepository.findAll(sort);
            List<PopularRes> data = new ArrayList<>();

            for(int i = 0; i < programs.size(); i++)
            {
                PopularRes tmpt = new PopularRes();
                tmpt.setThumbnail(programs.get(i).getThumbnail());
                tmpt.setTitle(programs.get(i).getTitle());

                data.add(tmpt);
            }
            return DefaultRes.res(StatusCode.OK, "조회 성공", data);
        } catch (Exception e)
        {
            log.info(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, "데이터베이스 에러");
        }
    }

    /**
     *  프로그램 신청
     * @param date
     * @return
     */
    public DefaultRes joinProgram(final int userIdx, final JoinProgramReq date){
        try{
            ProgramUser join = new ProgramUser();

            log.info(date.toString());

            join.setUserIdx(userIdx);
            join.setProgramIdx(date.getProgramIdx());
            join.setStartDate(date.getStartDate().plus(Period.ofDays(1)));
            join.setEndDate(date.getEndDate().plus(Period.ofDays(1)));

            log.info(join.toString());
            programUserRepository.save(join);
            programRepository.updateRegiNumber(date.getProgramIdx());
            log.info(join.toString());
            return DefaultRes.res(StatusCode.OK, "신청 완료");
        } catch (Exception e)
        {
            log.info(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, "데이터베이스 에러");
        }
    }
}
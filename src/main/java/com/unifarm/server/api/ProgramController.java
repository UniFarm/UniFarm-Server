package com.unifarm.server.api;

import com.unifarm.server.model.DefaultRes;
import com.unifarm.server.model.Program.JoinProgramReq;
import com.unifarm.server.service.ProgramService;
import com.unifarm.server.service.UserService;
import com.unifarm.server.utils.auth.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("programs")
public class ProgramController {

    private final ProgramService programService;
    private final UserService userService;

    public ProgramController(final ProgramService programService,
                             final UserService userService) {
        this.programService = programService;
        this.userService = userService;
    }

    //사용자 신청 프로그램 조회
    @Auth
    @GetMapping("/mine")
    public ResponseEntity<DefaultRes> getJoinProgram(@RequestHeader(value = "Authorization") final String header)
    {
        try {
            int userIdx = userService.authorization(header);

            if(userIdx == -1) return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.FORBIDDEN);
            else
            {
                return new ResponseEntity<>(programService.getJoinPrograms(userIdx), HttpStatus.OK);
            }

        } catch (Exception e)
        {
            log.error(e.getMessage());
            return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //프로그램 상세 조회
    @GetMapping("/{programIdx}")
    public ResponseEntity<DefaultRes> findProgram(@PathVariable("programIdx") final int programIdx)
    {
        try{

            return new ResponseEntity<>(programService.findProgram(programIdx), HttpStatus.OK);

        }catch (Exception e)
        {
            log.error(e.getMessage());
            return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //전공 기준 프로그램 조회
    @Auth
    @GetMapping("/major")
    public ResponseEntity<DefaultRes> findByMajor(@RequestHeader(value = "Authorization") final String header)
    {
        try{
            int userIdx = userService.authorization(header);

            if(userIdx == -1) return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.FORBIDDEN);
            else
            {
                return new ResponseEntity<>(programService.findByMajor(userIdx), HttpStatus.OK);
            }
        } catch (Exception e)
        {
            log.error(e.getMessage());
            return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //관심 키워드 기준 프로그램 조회
    @Auth
    @GetMapping("/keyword")
    public ResponseEntity<DefaultRes> findByKeword(@RequestHeader(value = "Authorization") final String header)
    {
        try{
            int userIdx = userService.authorization(header);

            if(userIdx == -1) return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.FORBIDDEN);
            else
            {
                return new ResponseEntity<>(programService.findByKeyword(userIdx), HttpStatus.OK);
            }
        } catch (Exception e)
        {
            log.error(e.getMessage());
            return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //     실시간 인기 조회
    @GetMapping("/popular")
    public ResponseEntity<DefaultRes> findPopular()
    {
        try{
            return new ResponseEntity<>(programService.findPopular(), HttpStatus.OK);
        } catch (Exception e)
        {
            log.error(e.getMessage());
            return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //프로그램 신청
    @Auth
    @PostMapping("")
    public ResponseEntity<DefaultRes> joinProgram(@RequestHeader(value = "Authorization") final String header,
                                                  @RequestBody final JoinProgramReq date)
    {
        try{
            int userIdx = userService.authorization(header);

            if(userIdx == -1) return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.FORBIDDEN);
            else
            {
                return new ResponseEntity<>(programService.joinProgram(userIdx, date), HttpStatus.OK);
            }
        } catch (Exception e)
        {
            log.error(e.getMessage());
            return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.unifarm.server.api;

import com.unifarm.server.domain.User;
import com.unifarm.server.model.DefaultRes;
import com.unifarm.server.model.LoginReq;
import com.unifarm.server.model.SignUpReq;
import com.unifarm.server.service.ProgramService;
import com.unifarm.server.service.UserService;
import com.unifarm.server.utils.auth.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.unifarm.server.model.DefaultRes.FAIL_DEFAULT_RES;

/**
 * Created By yw on 2019-09-04.
 */

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final ProgramService programService;

    public UserController(final UserService userService,
                          final ProgramService programService) {
        this.userService = userService;
        this.programService = programService;
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<DefaultRes> login(@RequestBody final LoginReq loginReq) {
        try {
            return new ResponseEntity<>(userService.login(loginReq), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Auth
    @GetMapping("/auth-check")
    public ResponseEntity authCheck(HttpServletRequest httpServletRequest) {
        try {
            int userIdx = (int) httpServletRequest.getAttribute("userIdx");
            return new ResponseEntity<>("인증 성공" , HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<DefaultRes> signUp(@RequestBody final SignUpReq signUpReq) {
        try {
            return new ResponseEntity<>(userService.saveUser(signUpReq), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/check")
    public ResponseEntity<DefaultRes> checkEmail(@RequestParam("email") final String email) {
        try {
            return new ResponseEntity<>(userService.validateEmail(email), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
package com.unifarm.server.service;

import com.unifarm.server.domain.User;
import com.unifarm.server.model.DefaultRes;
import com.unifarm.server.model.LoginReq;
import com.unifarm.server.respository.UserRepository;
import com.unifarm.server.utils.AES256Util;
import com.unifarm.server.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * Created By ds on 2019-08-20.
 */

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    private final S3FileUploadService s3FileUploadService;

    private final JwtService jwtService;

    public UserService(final UserRepository userRepository, final S3FileUploadService s3FileUploadService,
                       final JwtService jwtService) {
        this.userRepository = userRepository;
        this.s3FileUploadService = s3FileUploadService;
        this.jwtService = jwtService;
    }

    /**
     * 로그인
     * @param loginReq
     * @return
     */
    public DefaultRes login(final LoginReq loginReq) {
        Optional<User> user = userRepository.findByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword());
        if(user.isPresent()) {
            final String token = jwtService.create(user.get().getUserIdx());
            return DefaultRes.res(StatusCode.CREATED, "로그인 성공", token);
        }
        return DefaultRes.res(StatusCode.UNAUTHORIZED, "로그인 실패");
    }

    /**
     * 토큰 검사
     * @param jwt
     * @return
     */
    public int authorization(final String jwt) {
        final int userIdx = jwtService.decode(jwt).getUser_idx();
        if(userIdx == -1) return -1;

        final Optional<User> user = userRepository.findById(userIdx);
        if(!user.isPresent()) return -1;

        return userIdx;
    }

    /**
     * userIdx로 회원 조회
     * @param userIdx
     * @return
     */
    public DefaultRes<User> findByUserIdx(final int userIdx) {
        final Optional<User> user = userRepository.findById(userIdx);
        return user.map(value -> DefaultRes.res(StatusCode.OK, "사용자 정보 조회 완료", value))
                .orElseGet(() -> DefaultRes.res(StatusCode.NOT_FOUND, "사용자를 찾을 수 없습니다."));
    }

    /**
     * 회원 정보 저장
     * @param user
     * @return
     */
    public DefaultRes saveUser(final User user){
        try {
            AES256Util aes256Util = new AES256Util("MOJI-SERVER-ENCRYPT-TEST");
            user.setPassword(aes256Util.encrypt(user.getPassword()));
            userRepository.save(user);
            return DefaultRes.res(StatusCode.CREATED, "회원 가입 완료");
        } catch (Exception e) {
            System.out.println(e);
            return DefaultRes.res(StatusCode.DB_ERROR, "회원 가입 실패");
        }
    }

    /**
     * 이메일 중복 확인
     * @param email
     * @return
     */
    public DefaultRes validateEmail(final String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(value -> DefaultRes.res(StatusCode.BAD_REQUEST, "중복된 이메일입니다.", value)).orElseGet(() -> DefaultRes.res(StatusCode.OK, "사용 가능 합니다."));
    }
}

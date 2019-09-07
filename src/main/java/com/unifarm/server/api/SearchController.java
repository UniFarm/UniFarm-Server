package com.unifarm.server.api;

import com.unifarm.server.model.DefaultRes;
import com.unifarm.server.model.SearchProgramRes;
import com.unifarm.server.service.SearchService;
import com.unifarm.server.service.UserService;
import com.unifarm.server.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.unifarm.server.model.DefaultRes.FAIL_DEFAULT_RES;

/**
 * Created By yw on 2019-09-04.
 */

@Slf4j
@RestController
@RequestMapping("searches")
public class SearchController {
    private final UserService userService;
    private final SearchService searchService;

    public SearchController(final UserService userService,
                            final SearchService searchService) {
        this.userService = userService;
        this.searchService = searchService;
    }

    // 프로그램 검색

    @GetMapping("")
    public ResponseEntity<DefaultRes> searchProgram(@RequestParam("keyword") final String keyword) {
        try {
            return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, "검색 성공", searchService.findPrograms(keyword)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(DefaultRes.res(StatusCode.NOT_FOUND, "검색 실패"), HttpStatus.NOT_FOUND);
        }
    }


}
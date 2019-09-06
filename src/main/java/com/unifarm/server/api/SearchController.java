package com.unifarm.server.api;

import com.unifarm.server.model.DefaultRes;
import com.unifarm.server.model.SearchProgramRes;
import com.unifarm.server.service.SearchService;
import com.unifarm.server.service.UserService;
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
            System.out.println("keyword : " + keyword);
            SearchProgramRes searchProgramRes = new SearchProgramRes();
            if(searchService.findPrograms(keyword)!= null){
                searchProgramRes.setProgram(searchService.findPrograms(keyword));
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

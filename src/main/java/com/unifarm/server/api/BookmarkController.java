package com.unifarm.server.api;

import com.unifarm.server.model.DefaultRes;
import com.unifarm.server.model.Program.JoinProgramReq;
import com.unifarm.server.service.BookmarkService;
import com.unifarm.server.service.ProgramService;
import com.unifarm.server.service.UserService;
import com.unifarm.server.utils.auth.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("bookmarks")
public class BookmarkController {
    private final ProgramService programService;
    private final UserService userService;
    private final BookmarkService bookmarkService;

    public BookmarkController(final ProgramService programService,
                        final UserService userService,
                              final BookmarkService bookmarkService ) {
        this.programService = programService;
        this.userService = userService;
        this.bookmarkService = bookmarkService;
    }
    // 북마크 등록
    @GetMapping("/")
    public ResponseEntity<DefaultRes> regiBookmark(){
        System.out.println("yees");
        /*
        try{
            System.out.println("yes");

            if(userIdx == -1) return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.FORBIDDEN);
            else
            {
                return new ResponseEntity<>(bookmarkService.addBookmark(userIdx, programIdx), HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        */
        return null;
    }

}

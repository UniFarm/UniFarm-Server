package com.unifarm.server.service;

import com.unifarm.server.domain.ProgramBookmark;
import com.unifarm.server.model.DefaultRes;
import com.unifarm.server.respository.ProgramBookmarkRepository;
import com.unifarm.server.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookmarkService {
    private final ProgramBookmarkRepository programBookmarkRepository;

    public BookmarkService(final ProgramBookmarkRepository programBookmarkRepository) {
        this.programBookmarkRepository = programBookmarkRepository;
    }

    public DefaultRes addBookmark(final int userIdx, final int programIdx) {
        try {
            ProgramBookmark programBookmark = new ProgramBookmark();
            programBookmark.setProgramIdx(programIdx);
            programBookmark.setUserIdx(userIdx);
            programBookmarkRepository.save(programBookmark);
            return DefaultRes.res(StatusCode.CREATED, "북마크 등록 되었습니다", programBookmarkRepository.findAllByUserIdx(userIdx));
        }
        catch(Exception e) {
            return DefaultRes.res(StatusCode.DB_ERROR, "북마크 등록 실패했습니다.");
        }
    }
}

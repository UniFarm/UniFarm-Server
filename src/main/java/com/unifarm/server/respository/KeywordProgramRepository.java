package com.unifarm.server.respository;

import com.unifarm.server.domain.KeywordProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KeywordProgramRepository extends JpaRepository<KeywordProgram, Integer> {

    Optional<List<KeywordProgram>> findByProgramIdx(int programIdx);
    Optional<KeywordProgram> findByKeywordIdx(final int keywordIdx);

}

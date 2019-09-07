package com.unifarm.server.respository;

import com.unifarm.server.domain.Keyword;
import com.unifarm.server.domain.KeywordSearch;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface KeywordSearchRepository extends JpaRepository<KeywordSearch, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Program program set program.viewCount = program.viewCount + 1 where program.programIdx = :programIdx")
    void updateSearchCount(@Param("") int programIdx);
}

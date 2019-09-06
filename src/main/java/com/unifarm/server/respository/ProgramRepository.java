package com.unifarm.server.respository;

import com.unifarm.server.domain.Program;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface ProgramRepository extends JpaRepository<Program, Integer> {

    Optional<Program> findByProgramIdx(int programIdx);

    @Modifying
    @Transactional
    @Query("UPDATE Program program set program.viewCount = program.viewCount + 1 where program.programIdx = :programIdx")
    void updateViewCount(@Param("programIdx") int programIdx);
}
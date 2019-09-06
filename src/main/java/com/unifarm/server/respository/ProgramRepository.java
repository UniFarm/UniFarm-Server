package com.unifarm.server.respository;

import com.unifarm.server.domain.Keyword;
import com.unifarm.server.domain.KeywordProgram;
import com.unifarm.server.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgramRepository  extends JpaRepository<Program, Integer> {
    Optional<Program> findByProgramIdx(final int programIdx);
}

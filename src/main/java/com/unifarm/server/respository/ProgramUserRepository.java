package com.unifarm.server.respository;

import com.unifarm.server.domain.Program;
import com.unifarm.server.domain.ProgramUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgramUserRepository extends JpaRepository<ProgramUser, Integer> {
    Optional<List<ProgramUser>> findByUserIdx(int userIdx);
}

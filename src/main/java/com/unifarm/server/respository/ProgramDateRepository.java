package com.unifarm.server.respository;

import com.unifarm.server.domain.ProgramDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgramDateRepository extends JpaRepository<ProgramDate, Integer> {

    Optional<List<ProgramDate>> findByProgramIdx(int programIdx);

}

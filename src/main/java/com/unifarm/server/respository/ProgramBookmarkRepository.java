package com.unifarm.server.respository;

import com.unifarm.server.domain.ProgramBookmark;
import com.unifarm.server.domain.ProgramDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgramBookmarkRepository extends JpaRepository<ProgramBookmark, Integer > {
    Optional<List<ProgramBookmark>> findAllByUserIdx(int userIdx);
}

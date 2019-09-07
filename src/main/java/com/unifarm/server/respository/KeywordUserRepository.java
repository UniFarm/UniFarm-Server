package com.unifarm.server.respository;

import com.unifarm.server.domain.Keyword;
import com.unifarm.server.domain.KeywordUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KeywordUserRepository extends JpaRepository<KeywordUser, Integer> {

    Optional<List<KeywordUser>> findByUserIdx(final int userIdx);

}

package com.unifarm.server.respository;

import com.unifarm.server.domain.Keyword;
import com.unifarm.server.domain.KeywordUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordUserRepository extends JpaRepository<KeywordUser, Integer> {

}

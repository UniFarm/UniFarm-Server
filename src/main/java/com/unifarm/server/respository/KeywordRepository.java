package com.unifarm.server.respository;

import com.unifarm.server.domain.Keyword;

import com.unifarm.server.domain.KeywordProgram;

import com.unifarm.server.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, Integer> {


    Optional<Keyword> findByKeywordIdx(int keywordIdx);

    Optional<List<Keyword>> findByInfo(final String keyword);

    Optional<List<Keyword>> findAllByInfoContaining(final String keyword);
}


package com.unifarm.server.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "keyword_search")
public class KeywordSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int keywordIdx;
    private int searchedCount;
}
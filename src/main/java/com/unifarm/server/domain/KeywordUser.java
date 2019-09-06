
package com.unifarm.server.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "keyword_user")
public class KeywordUser {

    @Id
    private int keywordIdx;

    private int userIdx;
}

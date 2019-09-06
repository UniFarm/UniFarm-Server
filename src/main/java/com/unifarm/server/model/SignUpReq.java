package com.unifarm.server.model;

import com.unifarm.server.domain.Keyword;
import com.unifarm.server.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class SignUpReq {
    private User user;
    private List<String> keywords;
}

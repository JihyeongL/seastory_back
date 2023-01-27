package com.jl.seastory.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDto {

    private String grantType;
    private String memberName;
    private String accessToken;
    private Long accessTokenExpiresIn;

    public TokenDto(String grantType, String memberName, String accessToken, Long accessTokenExpiresIn){
        this.grantType = grantType;
        this.memberName = memberName;
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }
}

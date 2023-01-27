package com.jl.seastory.member.service;

import com.jl.seastory.exception.DuplicateUsernameException;
import com.jl.seastory.exception.LoginFailedException;
import com.jl.seastory.jwt.TokenProvider;
import com.jl.seastory.member.dao.MemberMapper;
import com.jl.seastory.member.dto.MemberDto;
import com.jl.seastory.member.dto.TokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AuthService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private TokenProvider tokenProvider;

    public AuthService(MemberMapper memberMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider){
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Transactional
    public MemberDto signup(MemberDto memberDto){
        if(memberMapper.selectByMemberId(memberDto.getMemberId()) != null){
            throw new DuplicateUsernameException("이미 존재하는 아이디 입니다.");
        }

        memberDto.setMemberPassword(passwordEncoder.encode(memberDto.getPassword()));
        int result = memberMapper.insertMember(memberDto);
        log.info("[AuthService] Member Insert Result {}", result > 0 ? "회원 가입 성공" : "회원 가입 실패");
        return memberDto;
    }

    @Transactional
    public TokenDto login(MemberDto memberDto){
        MemberDto member = memberMapper.findByMemberId(memberDto.getMemberId()).orElseThrow(() -> new LoginFailedException("등록되지 않은 아이디입니다."));

        if (!passwordEncoder.matches(memberDto.getMemberPassword(), member.getPassword())){
            throw new LoginFailedException("잘못된 비밀번호 입니다.");
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(member);

        return tokenDto;

    }

}

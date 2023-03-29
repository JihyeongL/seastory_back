package com.jl.seastory.member.service;

import com.jl.seastory.jwt.TokenProvider;
import com.jl.seastory.member.dao.MemberMapper;
import com.jl.seastory.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
//@Slf4j
public class MemberService {
    private final MemberMapper memberMapper;
//    private final PasswordEncoder passwordEncoder;
//    private final MemberDto memberDto;
//    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder,MemberDto memberDto){
//        this.memberMapper = memberMapper;
////        this.passwordEncoder = passwordEncoder;
//
//        this.memberDto = memberDto;
//    }

    public MemberDto selectMyInfo(@PathVariable String memberId){
        MemberDto member = memberMapper.selectByMemberId(memberId);
        return member;
    }

    @Transactional
    public Object updateMember(MemberDto memberDto) {
       return memberMapper.updateMember(memberDto);
    }



    public Object deleteMember(String memberId) {
        return memberMapper.deleteMember(memberId);
    }


}

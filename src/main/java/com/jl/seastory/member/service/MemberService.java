package com.jl.seastory.member.service;

import com.jl.seastory.member.dao.MemberMapper;
import com.jl.seastory.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public MemberDto selectMyInfo(@PathVariable String memberId){
        MemberDto member = memberMapper.selectByMemberId(memberId);
        return member;
    }


    public Object updateMember(MemberDto memberDto) {
       return memberMapper.updateMember(memberDto);
    }

    public Object deleteMember(String memberId) {
        return memberMapper.deleteMember(memberId);
    }
}

package com.jl.seastory.member.dao;

import com.jl.seastory.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    MemberDto selectByMemberId(String memberId);

    int insertMember(MemberDto memberDto);

    Optional<MemberDto> findByMemberId(String memberId);

    int updateMember(MemberDto memberDto);

    int deleteMember(String memberId);
}

package com.jl.seastory.member.service;

import com.jl.seastory.exception.UserNotFoundException;
import com.jl.seastory.member.dao.MemberMapper;
import com.jl.seastory.member.dto.MemberDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    public CustomUserDetailsService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        return memberMapper.findByMemberId(memberId)
                .map(user -> addAuthorities(user))
                .orElseThrow(()-> new UserNotFoundException(memberId + "찾을 수 없습니다."));
    }

    private MemberDto addAuthorities(MemberDto memberDto){
        memberDto.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(memberDto.getMemberRole())));

        return memberDto;
    }
}

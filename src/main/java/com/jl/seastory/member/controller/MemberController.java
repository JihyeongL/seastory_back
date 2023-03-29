package com.jl.seastory.member.controller;

import com.jl.seastory.common.ResponseDto;
import com.jl.seastory.member.dto.MemberDto;
import com.jl.seastory.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;
    private List<MemberDto> members;

    public MemberController(MemberService memberService, PasswordEncoder passwordEncoder){
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<ResponseDto> selectMyMemberInfo(@PathVariable String memberId){
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회성공", memberService.selectMyInfo(memberId)));
    }


    @PutMapping("/members/{memberId}")
    public ResponseEntity<ResponseDto> updateInfo(@PathVariable String memberId, @RequestBody MemberDto memberDto){
        MemberDto foundUser = memberService.selectMyInfo(memberId);
        foundUser.setAuthorities(memberService.selectMyInfo(memberId).getAuthorities());
        foundUser.setMemberId(memberId);
        foundUser.setMemberCode(memberService.selectMyInfo(memberId).getMemberCode());
        foundUser.setMemberRole(memberService.selectMyInfo(memberId).getMemberRole());
        foundUser.setMemberName(memberDto.getMemberName());
        foundUser.setMemberEmail(memberDto.getMemberEmail());
        foundUser.setMemberPassword(passwordEncoder.encode(memberDto.getMemberPassword()));

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "정보가 수정되었습니다.", memberService.updateMember(foundUser)));
    }

    @DeleteMapping("/members/{memberId}")
    public ResponseEntity<ResponseDto> deleteInfo(@PathVariable String memberId){
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "회원삭제 완료", memberService.deleteMember(memberId)));
    }
}

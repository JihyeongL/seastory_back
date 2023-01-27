package com.jl.seastory.member.controller;

import com.jl.seastory.common.ResponseDto;
import com.jl.seastory.member.dto.MemberDto;
import com.jl.seastory.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;
    private List<MemberDto> members;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<ResponseDto> selectMyMemberInfo(@PathVariable String memberId){
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회성공", memberService.selectMyInfo(memberId)));
    }

    @PutMapping("/members/{memberId}")
    public ResponseEntity<ResponseDto> updateInfo(@PathVariable String memberId, @RequestBody MemberDto memberDto){
        MemberDto foundUser = memberService.selectMyInfo(memberId);
        foundUser.setMemberPassword(memberService.selectMyInfo(memberId).getMemberPassword());
        foundUser.setAuthorities(memberService.selectMyInfo(memberId).getAuthorities());
        foundUser.setMemberId(memberId);
        foundUser.setMemberCode(memberService.selectMyInfo(memberId).getMemberCode());
        foundUser.setMemberRole(memberService.selectMyInfo(memberId).getMemberRole());


//        System.out.println("foundUser = " + foundUser);
//        System.out.println("memberDto = " + memberDto);
//        System.out.println("memberDto.getMemberName() = " + memberDto.getMemberName());

        foundUser.setMemberName(memberDto.getMemberName());
        foundUser.setMemberEmail(memberDto.getMemberEmail());

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "정보가 수정되었습니다.", memberService.updateMember(foundUser)));
    }

    @DeleteMapping("/members/{memberId}")
    public ResponseEntity<ResponseDto> deleteInfo(@PathVariable String memberId){
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "회원삭제 완료", memberService.deleteMember(memberId)));
    }
}

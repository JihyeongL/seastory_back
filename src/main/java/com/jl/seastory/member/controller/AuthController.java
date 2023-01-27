package com.jl.seastory.member.controller;


import com.jl.seastory.common.ResponseDto;
import com.jl.seastory.member.dto.MemberDto;
import com.jl.seastory.member.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody MemberDto memberDto){
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "회원가입 성공", authService.signup(memberDto)));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody MemberDto memberDto){
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "로그인 성공", authService.login(memberDto)));
    }
}

package com.example.springjpa.resources;

import com.example.springjpa.domain.dto.AuthSuccessDto;
import com.example.springjpa.domain.Member;
import com.example.springjpa.domain.dto.LendingDto;
import com.example.springjpa.domain.dto.MemberDto;
import com.example.springjpa.service.MemberService;
import com.example.springjpa.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberResource {

    private final MemberService memberService;

    private final TokenService tokenService;

    public MemberResource(MemberService memberService, TokenService tokenService) {
        this.memberService = memberService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        List<MemberDto> members = memberService.getAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthSuccessDto> login(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        Member member = memberService.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        AuthSuccessDto authSuccessDto = new AuthSuccessDto(token, member.getUsername());
        return new ResponseEntity<>(authSuccessDto, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthSuccessDto> createMember(@Valid @RequestBody MemberDto memberDto) {
        AuthSuccessDto authSuccessDto = memberService.signup(memberDto);
        return new ResponseEntity<>(authSuccessDto, HttpStatus.CREATED);
    }

    @PostMapping("/borrow")
    public ResponseEntity<MemberDto> borrowBook(@Valid @RequestBody LendingDto lendingDto, JwtAuthenticationToken principal) {
        MemberDto borrowingMember = memberService.borrowBook(lendingDto, principal.getName());
        return new ResponseEntity<>(borrowingMember, HttpStatus.OK);
    }
}

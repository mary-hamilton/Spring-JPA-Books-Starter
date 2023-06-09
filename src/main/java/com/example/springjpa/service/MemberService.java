package com.example.springjpa.service;

import com.example.springjpa.domain.dto.AuthSuccessDto;
import com.example.springjpa.domain.Book;
import com.example.springjpa.domain.Member;
import com.example.springjpa.domain.SecurityUser;
import com.example.springjpa.domain.dto.LendingDto;
import com.example.springjpa.domain.dto.MemberDto;
import com.example.springjpa.repository.BookRepository;
import com.example.springjpa.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    private final BookRepository bookRepository;

    private final PasswordEncoder passwordEncoder;

    private final JpaUserDetailsService jpaUserDetailsService;

    private final TokenService tokenService;

    public MemberService(MemberRepository memberRepository, BookRepository bookRepository, PasswordEncoder passwordEncoder, JpaUserDetailsService jpaUserDetailsService, TokenService tokenService) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.passwordEncoder = passwordEncoder;
        this.jpaUserDetailsService = jpaUserDetailsService;
        this.tokenService = tokenService;
    }

    public AuthSuccessDto signup(MemberDto memberDto) {
        Member memberToSave = new Member(memberDto.getFirstName(), memberDto.getLastName(), memberDto.getUsername(), passwordEncoder.encode(memberDto.getPassword()), Member.USER_ROLE );
        memberRepository.save(memberToSave);
        SecurityUser securityUser = (SecurityUser) jpaUserDetailsService.loadUserByUsername(memberToSave.getUsername());
        String token = tokenService.generateToken(securityUser);
        return new AuthSuccessDto(token, memberToSave.getUsername());
    }


    public List<MemberDto> getAllMembers() {
        return memberRepository
                .findAll()
                .stream()
                .map(Member::dtoWithBorrowedBooks)
                .collect(Collectors.toList());
    }

    public MemberDto borrowBook(LendingDto lendingDto, String principal) {
        Book foundBook = bookRepository
                .findById(lendingDto.getBookId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find book"));
        Member foundMember = memberRepository
                .findMemberByUsername(principal)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find member"));
        foundMember.getBorrowedBooks().add(foundBook);
        return memberRepository.save(foundMember).dtoWithBorrowedBooks();
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findMemberByUsername(username);
    }

    public void save(Member member) {
        memberRepository.save(member);
    }


}

package com.example.spring.domain.auth.application;

import com.example.spring.domain.user.domain.Member;
import com.example.spring.domain.user.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        Optional<Member> member = memberRepository.findByEmail(email);
//        if (member.isPresent()) {
//            return new Member(member.get());
//        }
//
//        throw new UsernameNotFoundException("유효하지 않는 유저이거나, 사장입니다.");
//    }
//
//    public UserDetails loadUserById(Long id) {
//        Optional<Member> member = memberRepository.findById(id);
//        if (member.isPresent()) {
//            return UserPrincipal.createUser(user.get());
//        }
//
//        throw new UsernameNotFoundException("유효하지 않는 유저이거나, 사장입니다.");
//    }

}

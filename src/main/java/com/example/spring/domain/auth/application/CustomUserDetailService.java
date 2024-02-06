package com.example.spring.domain.auth.application;

import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.member.domain.repository.MemberRepository;
import com.example.spring.global.apiResponse.code.status.ErrorStatus;
import com.example.spring.global.apiResponse.exception.GeneralException;
import com.example.spring.global.config.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            return UserPrincipal.createUser(member.get());
        }
        throw new GeneralException(ErrorStatus.MEMBER_NOT_FOUND);
    }

    public UserDetails loadUserById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return UserPrincipal.createUser(member.get());
        }
        throw new GeneralException(ErrorStatus.MEMBER_NOT_FOUND);
    }

}

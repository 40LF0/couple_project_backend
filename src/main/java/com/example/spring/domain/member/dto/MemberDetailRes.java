package com.example.spring.domain.member.dto;

import com.example.spring.domain.member.domain.Member;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor

public class MemberDetailRes {
    private String name;

    private String profileUrl;

    public static MemberDetailRes toDto(Member member){
        return MemberDetailRes.builder()
                .name(member.getName())
                .profileUrl(member.getProfileUrl())
                .build();
    }
}

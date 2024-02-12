package com.example.spring.domain.member.dto;

import lombok.Data;

@Data
public class UpdateMemberDetailReq {
    private String name;
    private String profileUrl;
}

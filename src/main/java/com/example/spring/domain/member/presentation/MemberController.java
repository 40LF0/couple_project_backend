package com.example.spring.domain.member.presentation;

import com.example.spring.domain.member.application.MemberService;
import com.example.spring.domain.member.dto.MemberDetailRes;
import com.example.spring.domain.member.dto.UpdateMemberDetailReq;
import com.example.spring.global.apiResponse.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    public ApiResponse<MemberDetailRes> getCurrentMember() {
        return ApiResponse.onSuccess(memberService.getCurrentUser());
    }

    @PutMapping("")
    public ApiResponse<MemberDetailRes> updateMember(@RequestBody UpdateMemberDetailReq request) {
        return ApiResponse.onSuccess(memberService.updateMemberDetail(request));
    }
}

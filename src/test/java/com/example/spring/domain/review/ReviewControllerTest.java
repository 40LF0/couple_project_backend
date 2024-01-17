package com.example.spring.domain.review;

import com.example.spring.domain.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MemberService memberService;
    private Long memberId;
    // 테스트 전에 실행되는 메소드
    @BeforeEach
    public void setup() {
        // 더미 멤버 데이터 생성
        memberId = memberService.saveAndGetDummyID();
    }

    @Test
    public void testCreateReviewWithValidData() throws Exception {
        String reviewJson = String.format(
                "{\"memberId\": %d, \"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                        "\"spotList\": [\"한강\"], " +
                        "\"imageUrlList\": [\"url1\"]}", memberId);

        mockMvc.perform(post("/reviews/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isOk());
    }
    @Test
    public void testCreateReviewWithEmptyData() throws Exception {
        String reviewJson = "{}";

        mockMvc.perform(post("/reviews/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateReviewWithEmptyMemberId() throws Exception {
        String reviewJson = String.format(
                "{\"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                        "\"spotList\": [\"한강\"], " +
                        "\"imageUrlList\": [\"url1\"]}");

        mockMvc.perform(post("/reviews/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateReviewWithEmptyTitle() throws Exception {
        String reviewJson = String.format(
                "{\"memberId\": %d, \"body\": \"글 1\", " +
                        "\"spotList\": [\"한강\"], " +
                        "\"imageUrlList\": [\"url1\"]}", memberId);

        mockMvc.perform(post("/reviews/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateReviewWithEmptyBody() throws Exception {
        String reviewJson = String.format(
                "{\"memberId\": %d, \"title\": \"정상 리뷰1\", " +
                        "\"spotList\": [\"한강\"], " +
                        "\"imageUrlList\": [\"url1\"]}", memberId);

        mockMvc.perform(post("/reviews/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testCreateReviewWithNullSpotList() throws Exception {
        String reviewJson = String.format(
                "{\"memberId\": %d, \"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                        "\"imageUrlList\": [\"url1\"]}", memberId);

        mockMvc.perform(post("/reviews/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateReviewWithEmptySpotList() throws Exception {
        String reviewJson = String.format(
                "{\"memberId\": %d, \"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                        "\"spotList\": [], " +
                        "\"imageUrlList\": [\"url1\"]}", memberId);

        mockMvc.perform(post("/reviews/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testCreateReviewWithThreeSpots() throws Exception {
        String reviewJson = String.format(
                "{\"memberId\": %d, \"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                        "\"spotList\": [\"spot1\",\"spot2\",\"spot3\"], " +
                        "\"imageUrlList\": [\"url1\"]}", memberId);

        mockMvc.perform(post("/reviews/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isOk());
    }
    @Test
    public void testCreateReviewWithFourSpots() throws Exception {
        String reviewJson = String.format(
                "{\"memberId\": %d, \"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                        "\"spotList\": [\"spot1\",\"spot2\",\"spot3\",\"spot4\"], " +
                        "\"imageUrlList\": [\"url1\"]}", memberId);

        mockMvc.perform(post("/reviews/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testCreateReviewWithThreeSpotsWithDuplicateOne() throws Exception {
        String reviewJson = String.format(
                "{\"memberId\": %d, \"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                        "\"spotList\": [\"spot1\",\"spot2\",\"spot3\",\"spot3\"], " +
                        "\"imageUrlList\": [\"url1\"]}", memberId);

        mockMvc.perform(post("/reviews/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isOk());
    }
    @Test
    public void testCreateReviewWithNullImages() throws Exception {
        String reviewJson = String.format(
                "{\"memberId\": %d, \"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                        "\"spotList\": [\"한강\"], ", memberId);

        mockMvc.perform(post("/reviews/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateReviewWithEmptyImages() throws Exception {
        String reviewJson = String.format(
                "{\"memberId\": %d, \"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                        "\"spotList\": [\"한강\"], " +
                        "\"imageUrlList\": []}", memberId);

        mockMvc.perform(post("/reviews/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateReviewWithFiveImages() throws Exception {
        String reviewJson = String.format(
                "{\"memberId\": %d, \"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                        "\"spotList\": [\"한강\"], " +
                        "\"imageUrlList\": [\"url1\",\"url2\",\"url3\",\"url4\",\"url5\"]}", memberId);

        mockMvc.perform(post("/reviews/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateReviewWithSixImages() throws Exception {
        String reviewJson = String.format(
                "{\"memberId\": %d, \"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                        "\"spotList\": [\"한강\"], " +
                        "\"imageUrlList\": [\"url1\",\"url2\",\"url3\",\"url4\",\"url5\",\"url6\"]}", memberId);

        mockMvc.perform(post("/reviews/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateReviewWithFiveImagesWithOneDuplicateImage() throws Exception {
        String reviewJson = String.format(
                "{\"memberId\": %d, \"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                        "\"spotList\": [\"한강\"], " +
                        "\"imageUrlList\": [\"url1\",\"url2\",\"url3\",\"url4\",\"url5\",\"url5\"]}", memberId);

        mockMvc.perform(post("/reviews/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isOk());
    }
}

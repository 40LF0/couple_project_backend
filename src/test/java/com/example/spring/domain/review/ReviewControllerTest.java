package com.example.spring.domain.review;

import com.example.spring.domain.auth.presentation.AuthController;
import com.example.spring.domain.member.application.MemberService;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private String authorization;
    // 테스트 전에 실행되는 메소드
    @BeforeEach
    public void setup() throws Exception {
        // 회원가입을 위한 JSON 데이터
        String signUpJson = "{\"providerId\": \"1111\", \"nickname\": \"chung\", " +
                "\"email\": \"chung@gmail.com\", \"profileImgUrl\": \"chung_profile\", " +
                "\"provider\": \"kakao\"}";

        // 회원가입 API 호출
        MvcResult result = mockMvc.perform(post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signUpJson))
                .andExpect(status().isOk())
                .andReturn();

        // 응답에서 JSON 데이터 파싱
        String response = result.getResponse().getContentAsString();
        String accessToken = JsonPath.parse(response).read("$.result.accessToken", String.class);

        //Authorization 값 저장
        this.authorization = "Bearer " + accessToken;
    }

    @Test
    public void testCreateReviewWithValidData() throws Exception {
        String reviewJson = "{\"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                "\"spotList\": [\"한강\"], " +
                "\"imageUrlList\": [\"url1\"]}";

        mockMvc.perform(post("/reviews")
                        .header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateReviewWithOutAuthorization() throws Exception {
        String reviewJson = "{\"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                "\"spotList\": [\"한강\"], " +
                "\"imageUrlList\": [\"url1\"]}";

        mockMvc.perform(post("/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isUnauthorized());
    }
    @Test
    public void testCreateReviewWithEmptyData() throws Exception {
        String reviewJson = "{}";

        mockMvc.perform(post("/reviews")
                        .header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateReviewWithEmptyTitle() throws Exception {
        String reviewJson = "{\"body\": \"글 1\", " +
                "\"spotList\": [\"한강\"], " +
                "\"imageUrlList\": [\"url1\"]}";

        mockMvc.perform(post("/reviews")
                        .header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateReviewWithEmptyBody() throws Exception {
        String reviewJson = "{\"title\": \"정상 리뷰1\", " +
                "\"spotList\": [\"한강\"], " +
                "\"imageUrlList\": [\"url1\"]}";

        mockMvc.perform(post("/reviews")
                        .header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testCreateReviewWithNullSpotList() throws Exception {
        String reviewJson = "{\"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                "\"imageUrlList\": [\"url1\"]}";

        mockMvc.perform(post("/reviews")
                        .header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateReviewWithEmptySpotList() throws Exception {
        String reviewJson = "{\"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                "\"spotList\": [], " +
                "\"imageUrlList\": [\"url1\"]}";

        mockMvc.perform(post("/reviews")
                        .header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testCreateReviewWithThreeSpots() throws Exception {
        String reviewJson = "{\"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                "\"spotList\": [\"spot1\",\"spot2\",\"spot3\"], " +
                "\"imageUrlList\": [\"url1\"]}";

        mockMvc.perform(post("/reviews")
                        .header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isOk());
    }
    @Test
    public void testCreateReviewWithFourSpots() throws Exception {
        String reviewJson = "{\"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                "\"spotList\": [\"spot1\",\"spot2\",\"spot3\",\"spot4\"], " +
                "\"imageUrlList\": [\"url1\"]}";

        mockMvc.perform(post("/reviews")
                        .header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testCreateReviewWithThreeSpotsWithDuplicateOne() throws Exception {
        String reviewJson = "{\"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                "\"spotList\": [\"spot1\",\"spot2\",\"spot3\",\"spot3\"], " +
                "\"imageUrlList\": [\"url1\"]}";

        mockMvc.perform(post("/reviews")
                        .header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isOk());
    }
    @Test
    public void testCreateReviewWithNullImages() throws Exception {
        String reviewJson = "{\"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                "\"spotList\": [\"한강\"], ";

        mockMvc.perform(post("/reviews")
                        .header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateReviewWithEmptyImages() throws Exception {
        String reviewJson = "{\"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                "\"spotList\": [\"한강\"], " +
                "\"imageUrlList\": []}";

        mockMvc.perform(post("/reviews")
                        .header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateReviewWithFiveImages() throws Exception {
        String reviewJson = "{\"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                "\"spotList\": [\"한강\"], " +
                "\"imageUrlList\": [\"url1\",\"url2\",\"url3\",\"url4\",\"url5\"]}";

        mockMvc.perform(post("/reviews")
                        .header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateReviewWithSixImages() throws Exception {
        String reviewJson = "{\"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                "\"spotList\": [\"한강\"], " +
                "\"imageUrlList\": [\"url1\",\"url2\",\"url3\",\"url4\",\"url5\",\"url6\"]}";

        mockMvc.perform(post("/reviews")
                        .header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateReviewWithFiveImagesWithOneDuplicateImage() throws Exception {
        String reviewJson = "{\"title\": \"정상 리뷰1\", \"body\": \"글 1\", " +
                "\"spotList\": [\"한강\"], " +
                "\"imageUrlList\": [\"url1\",\"url2\",\"url3\",\"url4\",\"url5\",\"url5\"]}";

        mockMvc.perform(post("/reviews")
                        .header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isOk());
    }
}

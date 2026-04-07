package com.gohome.firstapp_app;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service // "나는 머리 쓰는 로직을 담당하는 녀석이야!" 라고 스프링에게 알려줍니다.
public class GeminiService {

    // 🚨 주의: 여기에 어제 AI Studio에서 발급받았던 본인의 API 키를 꼭 넣으세요!
    private final String API_KEY = "AIzaSyAWY8tNiQJKWf9_ezgqN0-SKv0hC83THUM"; 
    
    // 제미나이 1.5 Flash 모델의 주소입니다.
    private final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + API_KEY;

    public String askGemini(String prompt) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 제미나이에게 보낼 질문을 JSON(컴퓨터 언어) 형태로 포장합니다.
            String requestJson = "{\"contents\":[{\"parts\":[{\"text\":\"" + prompt + "\"}]}]}";
            HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

            // 구글 서버로 발사!!!
            ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);
            
            // 구글이 보낸 답변을 그대로 던져줍니다.
            return response.getBody(); 
            
        } catch (Exception e) {
            return "AI 호출 실패: " + e.getMessage();
        }
    }
}

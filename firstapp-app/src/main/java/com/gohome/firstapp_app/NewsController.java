package com.gohome.firstapp_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewsController {

    // 우리가 아까 만든 AI 심부름꾼을 불러옵니다!
    @Autowired
    private GeminiService geminiService;

    // 처음에 그냥 들어왔을 때 (GET 방식)
    @GetMapping("/")
    public String showIndexPage() {
        return "index"; 
    }

    // 사용자가 질문을 입력하고 '물어보기' 버튼을 눌렀을 때 (POST 방식)
    @PostMapping("/ask")
    public String askAi(@RequestParam String prompt, Model model) {
        
        // 1. AI 심부름꾼에게 사용자의 질문(prompt)을 전달하고 답변을 받습니다.
        String aiResponse = geminiService.askGemini(prompt);
        
        // 2. 받은 답변을 화면(View)에 전달하기 위해 바구니(Model)에 담습니다.
        model.addAttribute("response", aiResponse);
        model.addAttribute("userPrompt", prompt);
        
        // 3. 답변을 담아서 다시 메인 화면을 보여줍니다.
        return "index";
    }
}
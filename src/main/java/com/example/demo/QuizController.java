package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class QuizController {

    @GetMapping("/bai-kiem-tra")
    public String showQuiz(Model model) {
        return "quiz"; 
    }

    @PostMapping("/nop-bai")
    public String submitQuiz(@RequestParam Map<String, String> allParams, Model model) {
        
        int score = 0;
        int maxScore = 100;

        // --- CHẤM ĐIỂM PHẦN 1 (12 câu x 5đ = 60đ) ---
        // Câu 1: 120 (Ví dụ)
        if (allParams.getOrDefault("cau1", "").contains("120")) score += 5;
        // Câu 8: 40 và 240
        String cau8 = allParams.getOrDefault("cau8", "");
        if (cau8.contains("40") && cau8.contains("240")) score += 5;
        // Câu 10: 6 khúc và 5 lần
        String cau10 = allParams.getOrDefault("cau10", "");
        if (cau10.contains("6") && cau10.contains("5")) score += 5;
        // ... Các câu trắc nghiệm khác bạn có thể thêm logic tương tự tại đây ...

        // --- CHẤM ĐIỂM PHẦN 2 (Tự động chấm đáp án ngắn) ---
        // Bài 13: Đáp án 250 (10 điểm)
        if (allParams.getOrDefault("bai13", "").contains("250")) {
            score += 10;
        }

        // Bài 14: Đáp án 24 (15 điểm)
        if (allParams.getOrDefault("bai14", "").contains("24")) {
            score += 15;
        }

        // Bài 15: Đáp án 172 (15 điểm)
        if (allParams.getOrDefault("bai15", "").contains("172")) {
            score += 15;
        }
        
        model.addAttribute("diemSo", score);
        model.addAttribute("diemToiDa", maxScore);
        // Hiển thị lại đáp án của học sinh để đối chiếu
        model.addAttribute("dapAnHocSinh", allParams);

        return "result"; 
    }
}
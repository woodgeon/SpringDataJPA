package kr.ac.hansung.cse.hellospringdatajpa.web.controller;

import kr.ac.hansung.cse.hellospringdatajpa.service.UserService;
import kr.ac.hansung.cse.hellospringdatajpa.web.dto.SignupReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("signupForm", new SignupReq());
        return "signup";
    }

    @PostMapping("/signup")
    public String processingSignup(@ModelAttribute("signupForm") SignupReq signupForm, Model model) {
        // 1. 서비스 계층에게 해당 로직을 위임
        boolean success = userService.register(signupForm, model);

        // 2. 결과값에 따라서 회원가입의 성공 실패를 결정짓는다.
        if (success) {
            model.addAttribute("message", "회원가입이 완료되었습니다. 로그인 해주세요.");
            return "signin";
        }
        else {
            return "signup";
        }
    }
}

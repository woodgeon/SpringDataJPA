package kr.ac.hansung.cse.hellospringdatajpa.web.controller;

import jakarta.validation.Valid;
import kr.ac.hansung.cse.hellospringdatajpa.service.AuthService;
import kr.ac.hansung.cse.hellospringdatajpa.web.dto.SigninReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class SigninController {

    private final AuthService authService;

    @GetMapping("/signin")
    public String signinPage(Model model) {
        model.addAttribute("signinReq", new SigninReq());
        return "signin";
    }

    @PostMapping("/signin")
    public String processSignin(@Valid @ModelAttribute SigninReq signinReq,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            return "signin";
        }

        if (!authService.authenticate(signinReq)) {
            model.addAttribute("loginError", "이메일 또는 비밀번호가 틀렸습니다.");
            return "signin";
        }

        return "redirect:/products";
    }
}

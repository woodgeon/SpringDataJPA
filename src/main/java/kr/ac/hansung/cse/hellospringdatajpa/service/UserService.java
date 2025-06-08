package kr.ac.hansung.cse.hellospringdatajpa.service;

import kr.ac.hansung.cse.hellospringdatajpa.entity.Role;
import kr.ac.hansung.cse.hellospringdatajpa.entity.User;
import kr.ac.hansung.cse.hellospringdatajpa.repository.UserRepository;
import kr.ac.hansung.cse.hellospringdatajpa.web.dto.SignupReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean register(SignupReq signupForm, Model model) {
        // 이메일 중복 검증
        if (userRepository.findByEmail(signupForm.getEmail()).isPresent()) {
            model.addAttribute("error", "이미 가입된 이메일입니다.");
            return false;
        }
        // 비밀번호 & 비밀번호 체크 값 검증
        System.out.println(signupForm.getPassword());
        System.out.println(signupForm.getPasswordCheck());
        if (!signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return false;
        }
        // 통과하면, 등록 시작
        User user = new User();
        user.setEmail(signupForm.getEmail());
        user.setPassword(passwordEncoder.encode(signupForm.getPassword()));

        // 관리자 유무 (한성대학교 우편번호로 하드코딩)
        if (signupForm.getBranchNumber().equals("Hansung")) {
            user.setRole(Role.ADMIN);
        }
        else {
            user.setRole(Role.USER);
        }

        userRepository.save(user);

        return true;
    }
}

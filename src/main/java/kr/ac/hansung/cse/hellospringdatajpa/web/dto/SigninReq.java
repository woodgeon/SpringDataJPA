package kr.ac.hansung.cse.hellospringdatajpa.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SigninReq {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}

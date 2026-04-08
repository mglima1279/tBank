package com.tbank.test.dto;

import com.tbank.test.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRequest {
    private String name;

    private String email;

    private String tel;

    private String cpf;

    private String password;

    public User toEntity() {
        User user = new User();

        user.setName(this.name);
        user.setEmail(this.email);
        user.setTel(this.tel);
        user.setCpf(this.cpf);

        return user;
    }
}

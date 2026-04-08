package com.tbank.test.dto;

import com.tbank.test.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private long id;
    private String name;
    private String email;
    private String tel;
    private String cpf;

    static UserResponse fromEntity(User user) {
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setTel(user.getTel());
        response.setCpf(user.getCpf());

        return response;
    }
}

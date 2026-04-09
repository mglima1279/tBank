package com.tbank.test.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tbank.test.dto.UserRequest;
import com.tbank.test.entities.Account;
import com.tbank.test.entities.User;
import com.tbank.test.repositories.AccountRepository;
import com.tbank.test.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserRequest request) {

        User user = request.toEntity();

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user = userRepository.save(user);

        if (user == null) {
            throw new RuntimeException("Error creating user");
        }

        Account account = new Account();
        account.setBalance(0);
        account.setUser(user);

        accountRepository.save(account);

        return userRepository.save(user);
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("404 - User not found"));
    }

    public User findByCpf(String cpf) {
        return userRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("404 - User not found with cpf: " + cpf));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User updateUser(long id, UserRequest request) {
        User user = findById(id);

        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getTel() != null) {
            user.setTel(request.getTel());
        }
        if (request.getCpf() != null) {
            user.setCpf(request.getCpf());
        }

        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void validatePassword(UserRequest request) {
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new RuntimeException("422 - Password is required");
        }
        if (request.getCpf() == null || request.getCpf().isEmpty()) {
            throw new RuntimeException("422 - CPF is required");
        }

        User user = findByCpf(request.getCpf());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("401 - Invalid password");
        }
    }
}

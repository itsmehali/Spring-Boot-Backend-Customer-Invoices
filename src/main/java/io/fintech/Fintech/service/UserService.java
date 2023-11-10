package io.fintech.Fintech.service;

import io.fintech.Fintech.domain.User;
import io.fintech.Fintech.dto.UserDTO;

public interface UserService {
    UserDTO createUser(User user);
    UserDTO getUserByEmail(String email);
    void sendVerificationCode(UserDTO user);
    UserDTO verifyCode(String email, String code);
    void resetPassword(String email);
    UserDTO verifyPasswordKey(String key);
    void renewPassword(String key, String password, String confirmPassword);
    UserDTO verifyAccountKey(String key);
}

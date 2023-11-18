package io.fintech.Fintech.service;

import io.fintech.Fintech.domain.User;
import io.fintech.Fintech.dto.UserDTO;
import io.fintech.Fintech.form.UpdateForm;

public interface UserService {
    UserDTO createUser(User user);
    UserDTO getUserByEmail(String email);
    void sendVerificationCode(UserDTO user);
    UserDTO verifyCode(String email, String code);
    void resetPassword(String email);
    UserDTO verifyPasswordKey(String key);
    void renewPassword(String key, String password, String confirmPassword);
    UserDTO verifyAccountKey(String key);
    UserDTO updateUserDetails(UpdateForm user);
    UserDTO getUserById(Long userId);
    void updatePassoword(Long id, String currentPassword, String newPassword, String confirmNewPassword);
}

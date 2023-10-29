package io.fintech.Fintech.service;

import io.fintech.Fintech.domain.User;
import io.fintech.Fintech.dto.UserDTO;

public interface UserService {
    UserDTO createUser(User user);
}

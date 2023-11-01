package io.fintech.Fintech.service.implementation;

import io.fintech.Fintech.domain.User;
import io.fintech.Fintech.dto.UserDTO;
import io.fintech.Fintech.repository.UserRepository;
import io.fintech.Fintech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.fintech.Fintech.dtomapper.UserDTOMapper.fromUser;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository<User> userRepository;
    @Override
    public UserDTO createUser(User user) {
        return fromUser(userRepository.create(user));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return fromUser(userRepository.getUserByEmail(email));
    }
    @Override
    public void sendVerificationCode(UserDTO user) {
        userRepository.sendVerificationCode(user);
    }

    @Override
    public User getUser(String email) {
        return userRepository.getUserByEmail(email);
    }
}

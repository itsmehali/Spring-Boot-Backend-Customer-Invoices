package io.fintech.Fintech.service.implementation;

import io.fintech.Fintech.domain.User;
import io.fintech.Fintech.dto.UserDTO;
import io.fintech.Fintech.dtomapper.UserDTOMapper;
import io.fintech.Fintech.repository.UserRepository;
import io.fintech.Fintech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository<User> userRepository;
    @Override
    public UserDTO createUser(User user) {
        return UserDTOMapper.fromUser(userRepository.create(user));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return UserDTOMapper.fromUser(userRepository.getUserByEmail(email));
    }

}

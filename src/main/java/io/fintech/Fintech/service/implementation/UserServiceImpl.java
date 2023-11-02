package io.fintech.Fintech.service.implementation;

import io.fintech.Fintech.domain.Role;
import io.fintech.Fintech.domain.User;
import io.fintech.Fintech.dto.UserDTO;
import io.fintech.Fintech.repository.RoleRepository;
import io.fintech.Fintech.repository.UserRepository;
import io.fintech.Fintech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.fintech.Fintech.dtomapper.UserDTOMapper.fromUser;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository<User> userRepository;
    private final RoleRepository<Role> roleRepository;
    @Override
    public UserDTO createUser(User user) {
        return maptoUserDTO(userRepository.create(user));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return maptoUserDTO(userRepository.getUserByEmail(email));
    }
    @Override
    public void sendVerificationCode(UserDTO user) {
        userRepository.sendVerificationCode(user);
    }

    @Override
    public UserDTO verifyCode(String email, String code) {
        return maptoUserDTO(userRepository.verifyCode(email,code));
    }

    private UserDTO maptoUserDTO(User user) {
        return fromUser(user, roleRepository.getRoleByUserId(user.getId()));
    }
}

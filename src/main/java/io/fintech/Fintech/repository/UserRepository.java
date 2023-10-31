package io.fintech.Fintech.repository;

import io.fintech.Fintech.domain.User;
import io.fintech.Fintech.dto.UserDTO;

import java.util.Collection;

public interface UserRepository<T extends User> {
    /* Basic CRUD Operations */
    T create(T data);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

    /* More Cpmplex operations */
    User getUserByEmail(String email);
    //void sendVerificationCode(UserDTO user);
}
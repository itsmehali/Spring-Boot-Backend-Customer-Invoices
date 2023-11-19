package io.fintech.Fintech.service;

import io.fintech.Fintech.domain.Role;

import java.util.Collection;

public interface RoleService {
    Role getRoleByUserId(Long id);
    Collection<Role> getRoles();
}

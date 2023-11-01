package io.fintech.Fintech.service;

import io.fintech.Fintech.domain.Role;

public interface RoleService {
    Role getRoleByUserId(Long id);
}

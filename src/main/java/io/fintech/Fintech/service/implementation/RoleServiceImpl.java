package io.fintech.Fintech.service.implementation;

import io.fintech.Fintech.domain.Role;
import io.fintech.Fintech.repository.RoleRepository;
import io.fintech.Fintech.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository<Role> roleRepository;
    @Override
    public Role getRoleByUserId(Long id) {
        return roleRepository.getRoleByUserId(id);
    }
}

package com.nguyenmauhuy.authentication.repository;

import com.nguyenmauhuy.authentication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findAllByIdIn(List<Long> ids);
}
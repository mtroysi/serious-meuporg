package com.example.repository;

import com.example.enumeration.RoleEnum;
import com.example.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Morgane TROYSI on 16/05/17.
 */

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    Role findByCode(RoleEnum role);
}

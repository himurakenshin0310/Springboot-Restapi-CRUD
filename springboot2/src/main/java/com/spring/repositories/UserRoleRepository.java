package com.spring.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.UserRoleCompositeKey;
import com.spring.entity.UserRoles;

@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRoles, Long> {

}

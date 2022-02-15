package com.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

}

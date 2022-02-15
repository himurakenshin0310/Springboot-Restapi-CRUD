package com.spring.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.Product;
@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("select p from Product p where p.name like %?1%")
	Page<Product> findByName(String key,Pageable pageable);
	Page<Product> findAll(Pageable pageable);
	
}

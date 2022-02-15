package com.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.entity.Product;
import com.spring.model.ProductDTO;

public interface ProductService {
	List<Product> findAll();
	Optional<Product> findbyId(long id);
	Product save(ProductDTO prod);
	void deleteById(long id);
	ProductDTO findByIdDao(long id);
	Page<Product> findAll(Pageable pageable);
	Page<Product> findByName(String key,Pageable pageable);
}

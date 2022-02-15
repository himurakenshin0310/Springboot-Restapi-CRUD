package com.spring.service;

import java.util.List;
import java.util.Optional;


import com.spring.entity.Type;
import com.spring.model.TypeDTO;

public interface TypeService {
	List<Type> findAll();
	Optional<Type> findById(long id);
	Type save(TypeDTO type);
	void deleteById(long id);
	TypeDTO findByTypeId(long id);
	
}

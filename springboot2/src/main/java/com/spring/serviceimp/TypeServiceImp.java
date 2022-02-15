package com.spring.serviceimp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.Type;
import com.spring.model.TypeDTO;
import com.spring.repositories.ProductRepository;
import com.spring.repositories.TypeRepository;
import com.spring.service.TypeService;

@Service
@Transactional
public class TypeServiceImp implements TypeService {

	@Autowired
	private TypeRepository typeRepo;

	@Autowired
	private ProductRepository prodRepo;

	@Override
	public List<Type> findAll() {
		return typeRepo.findAll();
	}

	@Override
	public Optional<Type> findById(long id) {
		return typeRepo.findById(id);
	}

	@Override
	public Type save(TypeDTO type) {
		Type type2 = new Type();
		type2.setName(type.getName());
		return typeRepo.save(type2);
	}

	@Override
	public void deleteById(long id) {
		typeRepo.deleteById(id);
	}

	@Override
	public TypeDTO findByTypeId(long id) {
		Type t = findById(id).get();
		TypeDTO dao = new TypeDTO();
		dao.setName(t.getName());
		dao.setId(t.getId());
		return dao;
	}

}

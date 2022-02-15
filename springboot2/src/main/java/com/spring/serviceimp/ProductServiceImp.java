package com.spring.serviceimp;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.Product;
import com.spring.model.ProductDTO;
import com.spring.repositories.ProductRepository;
import com.spring.repositories.TypeRepository;
import com.spring.service.ProductService;

@Service
@Transactional
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private TypeRepository typeRepo;

	@Override
	public List<Product> findAll() {
		return productRepo.findAll();
	}

	@Override
	public Optional<Product> findbyId(long id) {
		return productRepo.findById(id);
	}

	@Override
	public Product save(ProductDTO prod) {
		Product p = new Product();
		p.setName(prod.getName());
		p.setPrice(prod.getPrice());
		p.setType(typeRepo.findById(prod.getType_id()).get());
		return productRepo.save(p);
	}

	@Override
	public void deleteById(long id) {
		productRepo.deleteById(id);
	}

	@Override
	public ProductDTO findByIdDao(long id) {
		Product p = findbyId(id).get();
		ProductDTO dao = new ProductDTO();
		dao.setId(p.getId());
		dao.setName(p.getName());
		dao.setPrice(p.getPrice());
		dao.setType_id(p.getType().getId());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		dao.setCreateAt(df.format(p.getCreateAt()));
		dao.setUpdateAt(df.format(p.getUpdateAt()));
		return dao;
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepo.findAll(pageable);
	}

	@Override
	public Page<Product> findByName(String key, Pageable page) {
		return productRepo.findByName(key, page);
	}

}

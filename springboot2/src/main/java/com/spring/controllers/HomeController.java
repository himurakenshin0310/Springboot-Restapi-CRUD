package com.spring.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.config.CustomUserDetail;
import com.spring.config.UserService;
import com.spring.entity.Product;
import com.spring.entity.Type;
import com.spring.entity.Users;
import com.spring.model.ProductDTO;
import com.spring.model.ResponObject;
import com.spring.model.TypeDTO;
import com.spring.model.UserDto;
import com.spring.service.ProductService;
import com.spring.service.TypeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
@Slf4j
public class HomeController {
	@Autowired
	private ProductService prodService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private UserService userService;

	@GetMapping(value = "all")
	public ResponseEntity<ResponObject> allProd() {
		List<ProductDTO> dto = prodService.findAll().parallelStream().map(ProductDTO::create)
				.collect(Collectors.toList());
		Map<String, Object> data = new HashMap<>();
		data.put("total products", dto.size());
		data.put("list products", dto);
		return ResponseEntity.status(HttpStatus.OK).body(ResponObject.form("ok", "succsess!", data));
	}

	@GetMapping(value = "getOne/{id}")
	public ResponseEntity<String> findOne(@PathVariable long id, @AuthenticationPrincipal CustomUserDetail auth) {
//		CustomUserDetail user = (CustomUserDetail) auth.;
		return new ResponseEntity<String>("" + auth.getPassword(), HttpStatus.OK);

		// return new ResponseEntity<ResponObject>(
//				ResponObject.form("ok", "find by id succsess", prodService.findByIdDao(id)), HttpStatus.OK);
	}

	@PostMapping(value = "addProd")
	public ResponseEntity<ResponObject> addProd(@Valid @RequestBody ProductDTO proNew) {
		System.out.println(proNew.getPrice());
//		prodService.save(proNew);
		return ResponseEntity.status(HttpStatus.OK).body(ResponObject.form("ok", "add new sussess", null));
	}

	@DeleteMapping(value = "deleteProd/{id}")
	public ResponseEntity<ResponObject> delete(@PathVariable long id) {
		prodService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ResponObject.form("ok", "delete succsess", null));
	}

	@GetMapping(value = "getType")
	public ResponseEntity<ResponObject> getAllType() {
		List<TypeDTO> listType = typeService.findAll().parallelStream().map(TypeDTO::create)
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(ResponObject.form("ok", "succsess!", listType));
	}

	@GetMapping(value = "getType/{id}")
	public ResponseEntity<ResponObject> getOneType(@PathVariable long id) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponObject.form("200", "sreach by id succsess!", typeService.findByTypeId(id)));
	}

	@PostMapping(value = "addType")
	public ResponseEntity<ResponObject> addType(@RequestBody TypeDTO type) {
		typeService.save(type);
		return ResponseEntity.status(HttpStatus.OK).body(ResponObject.form("ok", "create new type succsess!", type));
	}

	@DeleteMapping(value = "deleteType/{id}")
	public ResponseEntity<ResponObject> deleteType(@PathVariable long id) {
		typeService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ResponObject.form("ok", "delete succsess!", null));
	}

	@GetMapping(value = "prodpaginate")
	public ResponseEntity<ResponObject> paginate(@RequestParam(defaultValue = "0") long page) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (page >= 1)
			page += -1;
		Sort sortable = Sort.by("id").descending();
		Pageable paging = PageRequest.of((int) page, 5, sortable);
		Page<Product> pageTus = prodService.findAll(paging);
		List<ProductDTO> dto = pageTus.getContent().parallelStream().map(ProductDTO::create)
				.collect(Collectors.toList());
		data.put("page", pageTus.getNumber() + 1);
		data.put("total page", pageTus.getTotalPages());
		data.put("list products", dto);
		data.put("total products", pageTus.getTotalElements());
		return ResponseEntity.status(HttpStatus.OK).body(ResponObject.form("ok", "succsess!", data));
	}

	@GetMapping(value = "searching")
	public ResponseEntity<ResponObject> search(@RequestParam String keyword,
			@RequestParam(defaultValue = "0") int page) {
		if (page >= 1)
			page += -1;
		Pageable paging = PageRequest.of(page, 4);
		Page<Product> pageTus = prodService.findByName(keyword, paging);
		Map<String, Object> object = new HashMap<>();
		object.put("list products", pageTus.get().map(ProductDTO::create).collect(Collectors.toList()));
		object.put("total page", pageTus.getTotalPages());
		object.put("current page", pageTus.getNumber());
		object.put("total products", pageTus.getTotalElements());
//		object.put("page", pageTus);

		return ResponseEntity.status(HttpStatus.OK).body(ResponObject.form("ok", "succsess!", object));
	}

	@PostMapping("registration")
	public ResponseEntity<ResponObject> registration(@Valid @RequestBody UserDto user) throws Exception {
		userService.resisterNewUser(user);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponObject.form("200", "registration new account succsess!", null));
	}

	@PostMapping(value = "insertdata")
	public ResponseEntity<String> insertdata() {
		for (int i = 0; i <= 20; i++) {
			ProductDTO p = new ProductDTO();
			p.setName("sp " + System.currentTimeMillis());
			p.setPrice((i + 1) * new Random().nextInt(50) + 1);
			if (i >= 10)
				p.setType_id(4);
			else
				p.setType_id(9);
			prodService.save(p);
		}
		return ResponseEntity.status(HttpStatus.OK).body("insert succsess!!");
	}
}

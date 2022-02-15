package com.spring.model;

import com.spring.entity.Type;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeDTO {
	private long id;
	private String name;

	public static TypeDTO create(Type type) {
		TypeDTO t = new TypeDTO();
		t.setName(type.getName());
		t.setId(type.getId());
		return t;
	}
}

package com.spring.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "type")
@Setter
@Getter
public class Type {
	@Column(name = "id")
	@Setter(value = AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", unique = true)
	private String name;

	@Setter(value = AccessLevel.NONE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "type", cascade = CascadeType.ALL)
	private List<Product> products;

	@Setter(value = AccessLevel.NONE)
	@Column(name = "create_at")
	private Date createAt;

	@Setter(value = AccessLevel.NONE)
	@Column(name = "update_at")
	private Date updateAt;
}

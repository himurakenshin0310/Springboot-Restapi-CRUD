package com.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
@Embeddable
@Getter@Setter
public class UserRoleCompositeKey implements Serializable {
	@Column(name = "user_id")
	long userId;
	@Column(name = "role_id")
	long roleId;
}

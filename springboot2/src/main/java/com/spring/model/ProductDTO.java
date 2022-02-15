package com.spring.model;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.spring.entity.Product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	private long id;
	
	@DecimalMin("1.0")
	@DecimalMax("9999999999999.9")
	private double price;
	
	@Pattern(regexp = "^sp[A-Za-z0-9]{0,10}$", 
			message = "name cant null and begin with sp, after sp have max 10 character.")
	private String name;
	
	@DecimalMin("1")
	@DecimalMax("9999999999999")
	private long type_id;
	
	private String createAt;
	
	private String updateAt;

	public static ProductDTO create(Product prod) {
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy-mm-dd");
		ProductDTO d = new ProductDTO();
		d.setId(prod.getId());
		d.setName(prod.getName());
		d.setPrice(prod.getPrice());
		d.setType_id(prod.getType().getId());
		d.setCreateAt(dtf.format(prod.getCreateAt()));
		d.setUpdateAt(dtf.format(prod.getUpdateAt()));
		return d;
	}
}

package com.ta2khu75.entity;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="brands")
public class Brand {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	@Column(nullable = false)
	String image;
	String description;
	@OneToMany(mappedBy = "brand")
	List<Product> products;
}

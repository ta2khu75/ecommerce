package com.ta2khu75.entity;

import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name="products")
public class Product {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(nullable = false)
	String name;
	@Column(nullable = false)
	Double basePrice;
	@Nullable
    String description;
	@Column(nullable = false)
	String image;
	boolean active=true;
	@ManyToOne
    Brand brand;
	@ManyToOne
	Category category;
	@OneToMany(mappedBy = "product")
    List<ProductImage> productImages;
	@OneToMany(mappedBy = "product")
    List<ProductOptionType> productOptionTypes;
	@OneToMany(mappedBy = "product")
    List<ProductVariant> productVariants;
}

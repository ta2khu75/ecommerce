package com.ta2khu75.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "product_variants")
public class ProductVariant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@NotNull
	Integer remaining;
	@NotNull
	Double price;
	String image;
	@ManyToOne
	Product product;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "variant")
	Set<VariantOptionValue> variantOptionValues; 
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "variant")
	List<OrderItem> orders;
//	public void addOptionValue(ProductOptionValue optionValue) {
//		VariantOptionValue variantOptionValue = new VariantOptionValue();
//		variantOptionValue.setVariant(this);
//		variantOptionValue.setOptionValue(optionValue);
//		this.variantOptionValues.add(variantOptionValue);
//	}
}

package com.ta2khu75.entity;

import java.util.List;

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
@Table(name = "delivery_addresses")
public class DeliveryAddress {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String fromDistrict;
	String fromWard;
	String toDistrict;
	String toWard;
	String addressDetails;
	String phone;
	boolean defaultAddress;
    AddressType addressType;
	@ManyToOne
	Account account;
	@OneToMany(mappedBy = "deliveryAddress")
	List<Order> orders;
}

package com.ta2khu75.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
@Table(name="social_accounts")
public class SocialAccount {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
	String provider;
	String email;
//	@JsonBackReference
	@OneToOne(mappedBy = "socialAccount", fetch = FetchType.LAZY)
	Account account;
}

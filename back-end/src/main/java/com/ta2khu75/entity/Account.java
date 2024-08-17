package com.ta2khu75.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "accounts")
public class Account implements UserDetails{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(unique = true, nullable = true, columnDefinition = "VARCHAR(255) COLLATE utf8_unicode_ci")
	String email;
	@Column(nullable = false)
	String password;
	@Column(nullable = false)
	String firstName;
	@Column(nullable = false)
	String lastName;
	boolean enabled;
	boolean nonLocked=true;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	Role role;
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	@OneToMany(mappedBy = "account")
//    @JsonManagedReference
    List<ProductEvaluation> evaluations;
	@OneToOne
//	@JsonManagedReference
	SocialAccount socialAccount;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return nonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
}

package com.fajar.livestreaming.entities; 

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8242145043031638473L;

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;

	private Date dateCreated;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)

	@JoinTable(name = "user_authority",

			joinColumns = { @JoinColumn(name = "user_id") },

			inverseJoinColumns = { @JoinColumn(name = "authority_id") })

	private Set<Authority> authorities = new HashSet<>();

	public User() {

	}

	// getters and setters

}
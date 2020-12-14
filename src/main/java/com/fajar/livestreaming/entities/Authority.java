package com.fajar.livestreaming.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity

@Table(name = "authority")
@Data
public class Authority implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2534190215509155334L;

	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Enumerated(EnumType.STRING)

	private AuthorityType name;

	public Authority() {
	}

	public Authority(AuthorityType name) {

		this.name = name;
	}
}

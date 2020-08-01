package com.ifsaid.shark.entity;


import com.ifsaid.shark.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lzb
 * @since 2020-05-12
 */
@Entity
@ToString
@AllArgsConstructor
@Table(name = "tb_contact")
public class Contact extends BaseEntity implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String name;
	private String company;
	private String address;
	private String phone;
	private String job;

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	private String userId;


	public String getId() {
		return id;
	}

	public Contact setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Contact setName(String name) {
		this.name = name;
		return this;
	}

	public String getCompany() {
		return company;
	}

	public Contact setCompany(String company) {
		this.company = company;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Contact setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public Contact setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public Contact setUserId(String userId) {
		this.userId = userId;
		return this;
	}


}

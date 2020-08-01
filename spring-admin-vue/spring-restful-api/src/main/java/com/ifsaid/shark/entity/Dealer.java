package com.ifsaid.shark.entity;



import com.ifsaid.shark.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lzb
 * @since 2020-05-12
 */

@AllArgsConstructor
@Entity
@ToString
@Table(name = "tb_dealer")
public class Dealer extends BaseEntity implements Serializable {


	@Id
	private String id;
	private String name;
    /**
     * 1~市级 2~省级
     */
	private Integer level;
	private String area;
	private String manager;
	private String address;
	private String phone;

	private String userId;


	public String getId() {
		return id;
	}

	public Dealer setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Dealer setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getLevel() {
		return level;
	}

	public Dealer setLevel(Integer level) {
		this.level = level;
		return this;
	}

	public String getArea() {
		return area;
	}

	public Dealer setArea(String area) {
		this.area = area;
		return this;
	}

	public String getManager() {
		return manager;
	}

	public Dealer setManager(String manager) {
		this.manager = manager;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Dealer setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public Dealer setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public Dealer setUserId(String userId) {
		this.userId = userId;
		return this;
	}



}

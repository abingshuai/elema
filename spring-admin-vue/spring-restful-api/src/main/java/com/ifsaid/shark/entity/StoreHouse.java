package com.ifsaid.shark.entity;


import com.ifsaid.shark.common.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
 * @since 2020-05-17
 */
@Table(name = "tb_store_house")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StoreHouse extends BaseEntity implements Serializable{



	@Id
	private Integer id;
	private String area;
	private String name;
	private String manager;
	private String phone;
	private Integer status;
	private Integer max;
	private String address;
    /**
     * 现有库存
     */
	private Integer stock;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public StoreHouse setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getArea() {
		return area;
	}

	public StoreHouse setArea(String area) {
		this.area = area;
		return this;
	}

	public String getName() {
		return name;
	}

	public StoreHouse setName(String name) {
		this.name = name;
		return this;
	}

	public String getManager() {
		return manager;
	}

	public StoreHouse setManager(String manager) {
		this.manager = manager;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public StoreHouse setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public StoreHouse setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public Integer getMax() {
		return max;
	}

	public StoreHouse setMax(Integer max) {
		this.max = max;
		return this;
	}

	public Integer getStock() {
		return stock;
	}

	public StoreHouse setStock(Integer stock) {
		this.stock = stock;
		return this;
	}



}

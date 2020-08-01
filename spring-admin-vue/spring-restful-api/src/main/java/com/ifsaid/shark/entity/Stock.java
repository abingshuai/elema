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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_stock")
public class Stock extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Integer storeId;

	private String productId;

	private Integer productNums;

	private String productName;

	private Integer status;

	public String getStoreHouseName() {
		return storeHouseName;
	}

	public void setStoreHouseName(String storeHouseName) {
		this.storeHouseName = storeHouseName;
	}

	private String storeHouseName;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public Stock setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public Stock setStoreId(Integer storeId) {
		this.storeId = storeId;
		return this;
	}

	public String getProductId() {
		return productId;
	}

	public Stock setProductId(String productId) {
		this.productId = productId;
		return this;
	}

	public Integer getProductNums() {
		return productNums;
	}

	public Stock setProductNums(Integer productNums) {
		this.productNums = productNums;
		return this;
	}

	public String getProductName() {
		return productName;
	}

	public Stock setProductName(String productName) {
		this.productName = productName;
		return this;
	}

}

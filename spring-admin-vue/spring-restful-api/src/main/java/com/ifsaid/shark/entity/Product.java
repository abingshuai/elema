package com.ifsaid.shark.entity;



import com.ifsaid.shark.common.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


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
@Table(name = "tb_product")
public class Product extends BaseEntity implements Serializable{

    @Id
    private String id;
    private Integer nums;
    private Integer status;
    private String name;
    private String storeHouseId;
    private String location;

    public String getStoreHouseId() {
        return storeHouseId;
    }

    public void setStoreHouseId(String storeHouseId) {
        this.storeHouseId = storeHouseId;
    }

    public String getId() {
        return id;
    }

    public Product setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getNums() {
        return nums;
    }

    public Product setNums(Integer nums) {
        this.nums = nums;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Product setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }



    public String getLocation() {
        return location;
    }

    public Product setLocation(String location) {
        this.location = location;
        return this;
    }



}

package com.angelos.koinoxrhsta.impl.po;

import java.io.Serializable;

import com.angelos.koinoxrhsta.impl.po.keys.WarehouseKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@IdClass(WarehouseKey.class)
@Table(name = "TBWAREHOUSE")
public class Warehouse implements Serializable {

	/**
	 * buildingId
	 */
	@Id
	@Column(name = "BUILDING_ID", nullable = false)
	private Long buildingId;

	/**
	 * flatId
	 */
	@Id
	@Column(name = "FLAT_ID", nullable = false)
	private Long flatId;

	/**
	 * area
	 */
    @Column(name = "AREA")
    private Integer area;

    /**
     * name
     */
    @Column(name = "NAME", length = 2)
    private String name;
    
}
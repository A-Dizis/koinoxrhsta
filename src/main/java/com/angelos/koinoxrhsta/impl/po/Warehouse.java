package com.angelos.koinoxrhsta.impl.po;

import com.angelos.koinoxrhsta.impl.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.po.keys.WarehouseKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Data
@IdClass(WarehouseKey.class)
@Table(name = "TBWAREHOUSE")
public class Warehouse extends Key<WarehouseKey> {

    /**
	 * building - ID
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

	/**
	 * 
	 */
	@Version
	@Column(name = "LAST_VERSION")
	private Long lastVersion;
    
}
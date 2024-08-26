package com.angelos.koinoxrhsta.impl.po;

import com.angelos.koinoxrhsta.def.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.infrastructure.KeyImpl;
import com.angelos.koinoxrhsta.impl.po.keys.WarehouseKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@IdClass(WarehouseKey.class)
@Table(name = "TBWAREHOUSE")
public class Warehouse extends KeyImpl<WarehouseKey> implements Key<WarehouseKey> {

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

	/**
	 * @return
	 */
	public WarehouseKey getKey() {
		return super.getKey(WarehouseKey.class);
	}

	/**
	 * 
	 */
	public void setKey(WarehouseKey key) {
		super.setKey(key);
	}

}
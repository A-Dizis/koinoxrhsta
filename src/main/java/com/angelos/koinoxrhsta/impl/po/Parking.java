package com.angelos.koinoxrhsta.impl.po;

import com.angelos.koinoxrhsta.def.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.enums.Side;
import com.angelos.koinoxrhsta.impl.infrastructure.KeyImpl;
import com.angelos.koinoxrhsta.impl.po.keys.ParkingKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Data
@IdClass(ParkingKey.class)
@Table(name = "TBPARKING")
public class Parking extends KeyImpl<ParkingKey> implements Key<ParkingKey> {

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
	 * entrance
	 */
	@Column(name = "ENTRANCE_SIDE")
	@Enumerated(EnumType.ORDINAL)
	private Side entrance;

	/**
	 * 
	 */
	@Version
	@Column(name = "LAST_VERSION")
	private Long lastVersion;

	/**
	 * @return
	 */
	public ParkingKey getKey() {
		return super.getKey(ParkingKey.class);
	}

	/**
	 * 
	 */
	public void setKey(ParkingKey key) {
		super.setKey(key);
	}

}
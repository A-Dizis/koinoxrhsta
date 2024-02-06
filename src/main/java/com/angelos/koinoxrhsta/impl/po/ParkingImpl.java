package com.angelos.koinoxrhsta.impl.po;

import java.io.Serializable;

import com.angelos.koinoxrhsta.def.po.Building;
import com.angelos.koinoxrhsta.def.po.Parking;
import com.angelos.koinoxrhsta.impl.enums.Side;
import com.angelos.koinoxrhsta.impl.po.keys.ParkingKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@IdClass(ParkingKey.class)
@Table(name = "TBPARKING")
public class ParkingImpl implements Parking, Serializable {

	/**
	 * building
	 */
	@Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BUILDING_ID", referencedColumnName = "BUILDING_ID", nullable = false, insertable = false, updatable = false)
    private Building building;
	
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
    
}
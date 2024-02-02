package com.angelos.koinoxrhsta.impl.po;

import com.angelos.koinoxrhsta.impl.po.keys.ParkingKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@IdClass(ParkingKey.class)
@Table(name = "TBPARKING")
public class Parking {

	@Id
	@Column(name = "BUILDING_ID", nullable = false)
	private Long buildingId;

	@Id
	@Column(name = "FLAT_ID", nullable = false)
	private Long flatId;

    @Column(name = "AREA")
    private Integer area;

    @Column(name = "ENTRANCE_SIDE")
    private long entranceSide;
    
}
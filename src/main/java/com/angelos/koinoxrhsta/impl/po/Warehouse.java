package com.angelos.koinoxrhsta.impl.po;

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
public class Warehouse {

	@Id
	@Column(name = "BUILDING_ID", nullable = false)
	private Long buildingId;

	@Id
	@Column(name = "FLAT_ID", nullable = false)
	private Long flatId;

    @Column(name = "AREA", nullable = false)
    private Integer area;

    @Column(name = "NAME", nullable = false, length = 2)
    private String name;
}
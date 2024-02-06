package com.angelos.koinoxrhsta.impl.po;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.angelos.koinoxrhsta.def.po.Building;
import com.angelos.koinoxrhsta.def.po.Warehouse;
import com.angelos.koinoxrhsta.impl.po.keys.WarehouseKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Component
@Entity
@Data
@IdClass(WarehouseKey.class)
@Table(name = "TBWAREHOUSE")
public class WarehouseImpl implements Warehouse, Serializable {

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
     * name
     */
    @Column(name = "NAME", length = 2)
    private String name;
    
}
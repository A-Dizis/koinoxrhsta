package com.angelos.koinoxrhsta.impl.po;

import java.time.LocalDate;

import com.angelos.koinoxrhsta.impl.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.po.keys.BuildingKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;


@Entity
@Data
@IdClass(BuildingKey.class)
@Table(name = "TBBUILDING")
public class Building extends Key<BuildingKey> {

	/**
     * buildingId
     */
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "building_generator")
    @SequenceGenerator(name="building_generator", sequenceName = "SEQ_BUILDING_ID", allocationSize = 1)
    @Column(name = "BUILDING_ID", nullable = false)
	private Long buildingId;

	/**
     * ownershipMillis
     */
    @Column(name = "OWNERSHIP_MILLIS")
    private Integer ownershipMillis;
 
    /**
     * flats
     */
    @Column(name = "FLATS")
    private Integer flatsTotal;

    /**
     * floors
     */
    @Column(name = "FLOORS")
    private Integer floorsTotal;
    
    /**
     * postalCode
     */
    @Column(name = "POSTAL_CODE")
    private Integer postalCode;

    /**
     * addressName
     */
    @Column(name = "ADDRESS_NAME", length = 25)
    private String addressName;

    /**
     * addressNo
     */
    @Column(name = "ADDRESS_NO")
    private Integer addressNo;

    /**
     * builtDate
     */
    @Column(name = "BUILT_DT")
    private LocalDate builtDate;

    /**
	 * 
	 */
	@Version
	@Column(name = "LAST_VERSION")
	private Long lastVersion;
}

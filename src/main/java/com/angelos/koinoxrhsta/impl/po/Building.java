package com.angelos.koinoxrhsta.impl.po;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.angelos.koinoxrhsta.impl.po.keys.BuildingKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@IdClass(BuildingKey.class)
@Table(name = "TBBUILDING")
public class Building implements Serializable {
	
	/**
     * buildingId
     */
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "building_generator")
    @SequenceGenerator(name="building_generator", sequenceName = "SEQ_FLAT_ID", allocationSize = 1)
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
    private Integer flats;

    /**
     * floors
     */
    @Column(name = "FLOORS")
    private Integer floors;
    
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
    @Setter(value = AccessLevel.NONE) @Getter(value = AccessLevel.NONE) @EqualsAndHashCode.Exclude @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    private List<Flat> flat;
    
    /**
     * 
     */
    @Transient
    @Setter(value = AccessLevel.NONE) @Getter(value = AccessLevel.NONE) @EqualsAndHashCode.Exclude @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "building")
    private Bill bill;
    
}
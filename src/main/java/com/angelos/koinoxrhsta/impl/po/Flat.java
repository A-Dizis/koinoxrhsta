package com.angelos.koinoxrhsta.impl.po;

import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
@IdClass(FlatKey.class)
@Table(name = "TBFLAT")
public class Flat {

	/**
	 * flatId - ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flat_generator")
    @SequenceGenerator(name="flat_generator", sequenceName = "SEQ_FLAT_ID", allocationSize = 1)
    @Column(name = "FLAT_ID", nullable = false)
	private Long flatId;
	
	/**
	 * buildingId - ID
	 */
	@Id
    @Column(name = "BUILDING_ID", nullable = false)
	public Long buildingId;

	/**
	 * ownershipMillis
	 */
    @Column(name = "OWNERSHIP_MILLIS")
    private Integer ownershipMillis;
    
    /**
	 * floor
	 */
    @Column(name = "FLOOR")
    private Integer floor;

    /**
	 * flatName
	 */
    @Column(name = "FLAT_NAME", length = 2)
    private String flatName;
    
    /**
	 * building
	 */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BUILDING_ID", referencedColumnName = "BUILDING_ID", nullable = false, insertable = false, updatable = false)
    private Building building;
    
    /**
	 * flatSpec
	 */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns(
    	    {
    	    	@JoinColumn(name = "BUILDING_ID", referencedColumnName = "BUILDING_ID", nullable = false, insertable = false, updatable = false),
    	    	@JoinColumn(name = "FLAT_ID", referencedColumnName = "FLAT_ID", nullable = false, insertable = false, updatable = false)
    	    })
    private FlatSpec flatSpec;
    
    /**
	 * owner
	 */
    @Transient
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Owner owner;
}

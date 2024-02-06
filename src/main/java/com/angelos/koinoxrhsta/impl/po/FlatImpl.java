package com.angelos.koinoxrhsta.impl.po;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.angelos.koinoxrhsta.def.po.Building;
import com.angelos.koinoxrhsta.def.po.Flat;
import com.angelos.koinoxrhsta.def.po.FlatSpec;
import com.angelos.koinoxrhsta.def.po.Owner;
import com.angelos.koinoxrhsta.def.po.Parking;
import com.angelos.koinoxrhsta.def.po.Warehouse;
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
import lombok.Data;

@Component
@Entity
@Data
@IdClass(FlatKey.class)
@Table(name = "TBFLAT")
public class FlatImpl implements Flat, Serializable {

	/**
	 * flatId - ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flat_generator")
    @SequenceGenerator(name="flat_generator", sequenceName = "SEQ_FLAT_ID", allocationSize = 1)
    @Column(name = "FLAT_ID", nullable = false)
	private Long flatId;
	
    /**
	 * building
	 */
	@Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BUILDING_ID", referencedColumnName = "BUILDING_ID", nullable = false)
    private Building building;
	
    /**
	 * owner
	 */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "OWNER_ID")
    private Owner owner;

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
     * 
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns(
    		{
				@JoinColumn(name = "BUILDING_ID", referencedColumnName = "BUILDING_ID", nullable = false, insertable = false, updatable = false),
				@JoinColumn(name = "FLAT_ID", referencedColumnName = "FLAT_ID", nullable = false, insertable = false, updatable = false)
		})
    private Parking parking;
    
    /**
     * 
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns(
    	    {
    	    	@JoinColumn(name = "BUILDING_ID", referencedColumnName = "BUILDING_ID", nullable = false, insertable = false, updatable = false),
    	    	@JoinColumn(name = "FLAT_ID", referencedColumnName = "FLAT_ID", nullable = false, insertable = false, updatable = false)
    	    })
    private Warehouse warehouse;
    
}

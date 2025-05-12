package com.angelos.koinoxrhsta.impl.dto;

import com.angelos.koinoxrhsta.def.dto.DTO;
import com.angelos.koinoxrhsta.impl.po.Bill;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.FlatSpec;
import com.angelos.koinoxrhsta.impl.po.Parking;
import com.angelos.koinoxrhsta.impl.po.Warehouse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FlatDTO 
implements DTO <Flat> {

	/**
	 * id.
	 */
	private Long flatId;
	
    /**
	 * building - ID
	 */
    private Long buildingId;
	
    /**
	 * owner
	 */
    private OwnerDTO owner;

	/**
	 * ownershipMillis
	 */
    private Integer ownershipMillis;
    
    /**
	 * floor
	 */
    private Integer floor;

    /**
	 * flatName
	 */
    private String flatName;
    
    /**
	 * flatSpec
	 */
    private FlatSpec flatSpec;
    
    /**
     * 
     */
    private Parking parking;
    
    /**
     * 
     */
    private Warehouse warehouse;
    
    /**
     * 
     */
    private Bill bill;
}

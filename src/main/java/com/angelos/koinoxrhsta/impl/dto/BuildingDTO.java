package com.angelos.koinoxrhsta.impl.dto;

import java.time.LocalDate;

import com.angelos.koinoxrhsta.def.dto.DTO;
import com.angelos.koinoxrhsta.impl.po.Building;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BuildingDTO 
implements DTO <Building> {

    /**
     * Id.
     */
    private Long buildingId;

    /**
     * ownershipMillis
     */
    private Integer ownershipMillis;

    /**
     * flats
     */
    private Integer flatsTotal;

    /**
     * floors
     */
    private Integer floorsTotal;

    /**
     * postalCode
     */
    private Integer postalCode;

    /**
     * addressName
     */
    private String addressName;

    /**
     * addressNo
     */
    private Integer addressNo;

    /**
     * builtDate
     */
    private LocalDate builtDate;
}

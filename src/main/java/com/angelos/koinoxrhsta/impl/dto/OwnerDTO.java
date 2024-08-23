package com.angelos.koinoxrhsta.impl.dto;

import java.time.LocalDate;

import com.angelos.koinoxrhsta.def.dto.DTO;
import com.angelos.koinoxrhsta.impl.enums.Sex;
import com.angelos.koinoxrhsta.impl.po.Owner;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OwnerDTO 
implements DTO <Owner> {

	/**
	 * id.
	 */
	private Long ownerId;
	
	/**
	 * name
	 */
    private String name;

    /**
	 * surname
	 */
    private String surname;

    /**
	 * birthDate
	 */
    private LocalDate birthDate;

    /**
	 * sex
	 */
    private Sex sex;
}
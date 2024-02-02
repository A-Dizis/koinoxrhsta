package com.angelos.koinoxrhsta.impl.po;

import com.angelos.koinoxrhsta.impl.po.keys.FlatSpecKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToOne;
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
@Table(name = "TBFLATSPEC")
@IdClass(FlatSpecKey.class)
public class FlatSpec {

	/**
	 * flatId - ID
	 */
	@Id
    @Column(name = "FLAT_ID", nullable = false)
	private Long flatId;
	
	/**
	 * buildingId - ID
	 */
    @Id
    @Column(name = "BUILDING_ID", nullable = false)
    private Long buildingId;

	/**
	 * totalArea
	 */
    @Column(name = "TOTAL_AREA")
    private Integer totalArea;

	/**
	 * balconiesArea
	 */
    @Column(name = "BALCONIES_AREA")
    private Integer balconiesArea;

	/**
	 * bedroomsNo
	 */
    @Column(name = "BEDROOMS_NO")
    private Integer bedroomsNo;

	/**
	 * bathroomsNo
	 */
    @Column(name = "BATHROOMS_NO")
    private Integer bathroomsNo;

	/**
	 * livingroomsNo
	 */
    @Column(name = "LIVINGROOMS_NO")
    private Integer livingroomsNo;

	/**
	 * kitchensNo
	 */
    @Column(name = "KITCHENS_NO")
    private Integer kitchensNo;

	/**
	 * balconiesNo
	 */
    @Column(name = "BALCONIES_NO")
    private Integer balconiesNo;
    
	/**
	 *
	 */
    @Setter(value = AccessLevel.NONE) @Getter(value = AccessLevel.NONE) @EqualsAndHashCode.Exclude @ToString.Exclude
    @Transient
    @OneToOne(fetch = FetchType.LAZY)
    private Flat flat;
}

package com.angelos.koinoxrhsta.impl.po;

import com.angelos.koinoxrhsta.def.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.infrastructure.KeyImpl;
import com.angelos.koinoxrhsta.impl.po.keys.FlatSpecKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "TBFLATSPEC")
@IdClass(FlatSpecKey.class)
public class FlatSpec extends KeyImpl<FlatSpecKey> implements Key<FlatSpecKey> {

	/**
	 * flatId - ID
	 */
	@Id
	@Column(name = "FLAT_ID", nullable = false)
	private Long flatId;

	/**
	 * building - ID
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
	@Version
	@Column(name = "LAST_VERSION")
	private Long lastVersion;

	/**
	 * 
	 */
	public FlatSpecKey getKey() {
		return super.getKey(FlatSpecKey.class);
	}

	/**
	* 
	*/
	public void setKey(FlatSpecKey key) {
		super.setKey(key);
	}

}

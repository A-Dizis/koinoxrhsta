package com.angelos.koinoxrhsta.def.po;

import java.time.LocalDate;

public interface Building {

	Long getBuildingId();

	void setBuildingId(Long buildingId);

	Integer getOwnershipMillis();

	void setOwnershipMillis(Integer ownershipMillis);

	Integer getFlatsTotal();

	void setFlatsTotal(Integer flatsTotal);

	Integer getFloorsTotal();

	void setFloorsTotal(Integer floorsTotal);

	Integer getPostalCode();

	void setPostalCode(Integer postalCode);

	String getAddressName();

	void setAddressName(String addressName);

	Integer getAddressNo();

	void setAddressNo(Integer addressNo);

	LocalDate getBuiltDate();

	void setBuiltDate(LocalDate builtDate);

}
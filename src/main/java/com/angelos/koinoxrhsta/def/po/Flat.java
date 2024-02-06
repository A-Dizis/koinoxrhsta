package com.angelos.koinoxrhsta.def.po;

import com.angelos.koinoxrhsta.def.po.commons.BuildingDependent;
import com.angelos.koinoxrhsta.def.po.commons.FlatIdDependent;

public interface Flat extends FlatIdDependent, BuildingDependent {

	Owner getOwner();

	void setOwner(Owner owner);

	Integer getOwnershipMillis();

	void setOwnershipMillis(Integer ownershipMillis);

	Integer getFloor();

	void setFloor(Integer floor);

	String getFlatName();

	void setFlatName(String flatName);

	FlatSpec getFlatSpec();

	void setFlatSpec(FlatSpec flatSpec);

	Parking getParking();

	void setParking(Parking parking);

	Warehouse getWarehouse();

	void setWarehouse(Warehouse warehouse);

}

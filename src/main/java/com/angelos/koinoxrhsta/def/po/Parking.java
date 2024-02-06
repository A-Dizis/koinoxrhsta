package com.angelos.koinoxrhsta.def.po;

import com.angelos.koinoxrhsta.impl.enums.Side;

public interface Parking {

	Building getBuilding();

	void setBuilding(Building building);

	Long getFlatId();

	void setFlatId(Long flatId);

	Integer getArea();

	void setArea(Integer area);

	Side getEntrance();

	void setEntrance(Side entrance);

}
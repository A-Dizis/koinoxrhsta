package com.angelos.koinoxrhsta.def.po;

import com.angelos.koinoxrhsta.def.po.commons.BuildingDependent;
import com.angelos.koinoxrhsta.def.po.commons.FlatIdDependent;

public interface Warehouse extends BuildingDependent, FlatIdDependent {

	Integer getArea();

	void setArea(Integer area);

	String getName();

	void setName(String name);

}
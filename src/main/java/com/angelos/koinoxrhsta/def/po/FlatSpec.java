package com.angelos.koinoxrhsta.def.po;

import com.angelos.koinoxrhsta.def.po.commons.BuildingDependent;
import com.angelos.koinoxrhsta.def.po.commons.FlatIdDependent;

public interface FlatSpec extends FlatIdDependent, BuildingDependent {

	Long getFlatId();

	void setFlatId(Long flatId);

	Building getBuilding();

	void setBuilding(Building building);

	Integer getTotalArea();

	void setTotalArea(Integer totalArea);

	Integer getBalconiesArea();

	void setBalconiesArea(Integer balconiesArea);

	Integer getBedroomsNo();

	void setBedroomsNo(Integer bedroomsNo);

	Integer getBathroomsNo();

	void setBathroomsNo(Integer bathroomsNo);

	Integer getLivingroomsNo();

	void setLivingroomsNo(Integer livingroomsNo);

	Integer getKitchensNo();

	void setKitchensNo(Integer kitchensNo);

	Integer getBalconiesNo();

	void setBalconiesNo(Integer balconiesNo);

}

package com.angelos.koinoxrhsta.def.op;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.angelos.koinoxrhsta.def.pw.BuildingPw;
import com.angelos.koinoxrhsta.def.pw.FlatPw;
import com.angelos.koinoxrhsta.def.pw.OwnerPw;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.Owner;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;

@Transactional(rollbackFor = Exception.class)
@Service
public class Executable {

	
	BuildingPw buildingPw;
	FlatPw flatPw;
	OwnerPw ownerPw;
	
	public Executable(BuildingPw buildingPw, FlatPw flatPw, OwnerPw ownerPw) {
		this.buildingPw = buildingPw;
		this.flatPw = flatPw;
		this.ownerPw = ownerPw;
	}



	public void execute() {

//		Building building = new Building();
//		building.setOwnershipMillis(1000);
//		building.setAddressName("kleidonos");
//		building.setAddressNo(67);
//		building.setBuiltDate(new Date(2024, Calendar.JANUARY, 20));
//		building.setFlats(10);
//		building.setPostalCode(16732);
//		building.setFloors(5);
//		
//		building = buildingPw.save(building);
//		System.out.println(building);
//		
//
//
//		Owner owner = new Owner();
//		owner.setName("atats");
//		owner.setSurname("kikos");
//		owner.setBirthDate(new Date(1970, Calendar.DECEMBER, 21));
//		owner.setSex((short) 0);
//
//		owner = ownerPw.save(owner);
//		System.out.println(owner);
//
//		Flat flat = new Flat();
//		flat.setBuildingId(building.getBuildingId());
//		flat.setOwnerId(owner.getOwnerId());
//		flat.setFlatName("C1");
//		flat.setFloor(3);
//		flat.setOwnershipMillis(105);
//		
//		flat = flatPw.save(flat);
//		System.out.println(flat);
		
		

		FlatKey key = new FlatKey();
		key.setBuildingId(1000022L);
		key.setFlatId(1000023L);
		
		Optional<Flat> flatOpt = flatPw.findById(key);
		
		System.out.println(flatOpt.get().getBuilding());
		System.out.println(flatOpt.get().getOwner());
		
	}
}

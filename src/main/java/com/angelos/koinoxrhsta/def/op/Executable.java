package com.angelos.koinoxrhsta.def.op;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.def.pw.BuildingPw;
import com.angelos.koinoxrhsta.def.pw.FlatPw;
import com.angelos.koinoxrhsta.def.pw.OwnerPw;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.FlatSpec;
import com.angelos.koinoxrhsta.impl.po.Owner;
import com.angelos.koinoxrhsta.impl.po.keys.BuildingKey;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;

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

//		BuildingKey key = new BuildingKey();
//		key.setBuildingId(1000005L);
//
//		Building building = buildingPw.getReferenceById(key);
//
//		System.out.println(building.getOwnershipMillis());
//		
		FlatKey key = new FlatKey();
		key.setBuildingId(1000005L);
		key.setFlatId(1002022L);

		Optional<Flat> flat = flatPw.findById(key);

		if(flat.isPresent()) {
			Owner owner = new Owner();
			owner.setName("atats");
			owner.setSurname("kikos");
			ownerPw.save(owner);
			
			System.out.println(owner.getName() + " " + owner.getSurname() + " " + flat.get().getBuilding().getAddressName() + " " + flat.get().getBuilding().getAddressNo());
			
			flat.get().setOwner(owner);
			
			flatPw.save(flat.get());
			
			System.out.println(owner.getName() + " " + owner.getSurname() + " " + flat.get().getBuilding().getAddressName() + " " + flat.get().getBuilding().getAddressNo());
			
		}
		
//		flatPw.save(flat);
//		
//		Owner giorgos = new Owner();
//		giorgos.setName("Giorgos");
//		giorgos.setSurname("Pajaros");
//		ownerPw.save(giorgos);
//	
//		flat.setOwner(giorgos);
//		
//		flatPw.save(flat);
		
	}
}

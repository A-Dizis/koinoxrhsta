package com.angelos.koinoxrhsta.def.op;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.angelos.koinoxrhsta.def.pw.BillPw;
import com.angelos.koinoxrhsta.def.pw.BuildingPw;
import com.angelos.koinoxrhsta.def.pw.FlatPw;
import com.angelos.koinoxrhsta.def.pw.OwnerPw;
import com.angelos.koinoxrhsta.def.pw.ParkingPw;
import com.angelos.koinoxrhsta.impl.po.Bill;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.keys.BillKey;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;

@Transactional(rollbackFor = Exception.class)
@Service
public class Executable {

	
	BuildingPw buildingPw;
	FlatPw flatPw;
	OwnerPw ownerPw;
	ParkingPw parkingPw;
	BillPw billPw;
	
	public Executable(BuildingPw buildingPw, FlatPw flatPw, OwnerPw ownerPw, ParkingPw parkingPw, BillPw billPw) {
		this.buildingPw = buildingPw;
		this.flatPw = flatPw;
		this.ownerPw = ownerPw;
		this.parkingPw = parkingPw;
		this.billPw = billPw;
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
		
//		BuildingKey key = new BuildingKey();
//		key.setBuildingId(1000022L);
//		Optional<Building> buildingOpt = buildingPw.findById(key);
//		Building building = buildingOpt.get();
//		System.out.println(building);
//		
//
//
//		Owner owner = new Owner();
//		owner.setName("Ruanda");
//		owner.setSurname("Marin");
//		owner.setBirthDate(LocalDate.of(1987, 9, 1));
//		owner.setSex(Sex.FEMALE);
//
//		owner = ownerPw.save(owner);
//		System.out.println(owner);
//		
//		Flat flat = new Flat();
//		flat.setBuildingId(building.getBuildingId());
//		flat.setOwnerId(owner.getOwnerId());
//		flat.setFlatName("E2");
//		flat.setFloor(5);
//		flat.setOwnershipMillis(45);
//		
//		flat = flatPw.save(flat);
//		System.out.println(flat);
//		
//		Parking parking = new Parking();
//		parking.setArea(10);
//		parking.setEntrance(Side.FRONT);
//		parking.setBuildingId(flat.getBuildingId());
//		parking.setFlatId(flat.getFlatId());
//		parking = parkingPw.save(parking);
//		
//		
//		System.out.println(flat);

		FlatKey key = new FlatKey();
		key.setBuildingId(1000022L);
		key.setFlatId(1001029L);
		
		Optional<Flat> flatOpt = flatPw.findById(key);
		
		System.out.println(flatOpt.get());
		
		Bill bill = new Bill();
		bill.setFlatId(flatOpt.get().getFlatId());
		bill.setBuildingId(flatOpt.get().getBuildingId());
		bill = billPw.save(bill);
		
		System.out.println(bill);
		BillKey keyBillKey = new BillKey();
		keyBillKey.setReceiptNo(bill.getReceiptNo());
		Optional<Bill> byId = billPw.findById(keyBillKey);
		System.out.println(byId.get());
		
	}
}

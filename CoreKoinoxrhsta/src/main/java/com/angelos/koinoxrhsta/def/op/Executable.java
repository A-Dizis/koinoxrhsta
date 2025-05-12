package com.angelos.koinoxrhsta.def.op;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.angelos.koinoxrhsta.def.pw.BillPw;
import com.angelos.koinoxrhsta.def.pw.BuildingPw;
import com.angelos.koinoxrhsta.def.pw.FlatPw;
import com.angelos.koinoxrhsta.def.pw.IssuerPw;
import com.angelos.koinoxrhsta.def.pw.OwnerPw;
import com.angelos.koinoxrhsta.def.pw.ParkingPw;
import com.angelos.koinoxrhsta.impl.enums.Sex;
import com.angelos.koinoxrhsta.impl.enums.Side;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.Owner;
import com.angelos.koinoxrhsta.impl.po.Parking;

@Transactional(rollbackFor = Exception.class)
@Service
public class Executable {

	BuildingPw buildingPw;
	FlatPw flatPw;
	OwnerPw ownerPw;
	ParkingPw parkingPw;
	BillPw billPw;
	IssuerPw issuerPw;
	
	public Executable(BuildingPw buildingPw, FlatPw flatPw, OwnerPw ownerPw, ParkingPw parkingPw, BillPw billPw, IssuerPw issuerPw) {
		this.buildingPw = buildingPw;
		this.flatPw = flatPw;
		this.ownerPw = ownerPw;
		this.parkingPw = parkingPw;
		this.billPw = billPw;
		this.issuerPw = issuerPw;
	}



	public void execute() {

		Building building = new Building();
		building.setOwnershipMillis(1000);
		building.setAddressName("Pesilopis");
		building.setAddressNo(161);
		building.setBuiltDate(LocalDate.of(1966, 12, 6));
		building.setFlatsTotal(12);
		building.setPostalCode(16233);
		building.setFloorsTotal(6);
		
		building = buildingPw.save(building);
		
//		BuildingKey key = new BuildingKey();
//		key.setBuildingId(1000022L);
//		Optional<Building> buildingOpt = buildingPw.findById(key);
//		Building building = buildingOpt.get();
//		System.out.println(building);
		


		Owner owner = new Owner();
		owner.setName("Tourlen");
		owner.setSurname("Sousi");
		owner.setBirthDate(LocalDate.of(1967, 3, 22));
		owner.setSex(Sex.FEMALE);

//		owner = ownerPw.save(owner);
		System.out.println(owner);
		
		
		Flat flat = new Flat();
		flat.setBuildingId(building.getBuildingId());
		flat.setOwner(owner);
		flat.setFlatName("A1");
		flat.setFloor(1);
		flat.setOwnershipMillis(180);
		flat = flatPw.save(flat);
		
		System.out.println(flat);

		
		Parking parking = new Parking();
		parking.setArea(9);
		parking.setEntrance(Side.BACK);
		parking.setBuildingId(building.getBuildingId());
		parking.setFlatId(flat.getFlatId());

//		parking = parkingPw.save(parking);
//		System.out.println(parking);
				
		flat.setParking(parking);
		flat = flatPw.save(flat);
		System.out.println(flat);


//		FlatKey key = new FlatKey();
//		key.setBuildingId(1000022L);
//		key.setFlatId(1001029L);
//		
//		Optional<Flat> flatOpt = flatPw.findById(key);
//		
//		System.out.println(flatOpt.get());
//		
//		Issuer issuer = new Issuer();
//		issuer.setName("Vodafone");
//		issuer.setServiceDescription("Telephoy internet and TV services.");
//		issuer = issuerPw.save(issuer);
//		
//		Bill bill = new Bill();
//		bill.setFlatId(flatOpt.get().getFlatId());
//		bill.setBuildingId(flatOpt.get().getBuildingId());
//		bill.setIssuer(issuer);
//		bill = billPw.save(bill);
//		
//		System.out.println(bill);
//		BillKey keyBillKey = new BillKey();
//		keyBillKey.setReceiptNo(bill.getReceiptNo());
//		Optional<Bill> byId = billPw.findById(keyBillKey);
//		System.out.println(byId.get());
		
	}
}

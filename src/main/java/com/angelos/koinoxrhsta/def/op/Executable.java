package com.angelos.koinoxrhsta.def.op;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.angelos.koinoxrhsta.def.po.Building;
import com.angelos.koinoxrhsta.def.po.Flat;
import com.angelos.koinoxrhsta.def.po.Owner;
import com.angelos.koinoxrhsta.def.po.Parking;
import com.angelos.koinoxrhsta.def.pw.BillPw;
import com.angelos.koinoxrhsta.def.pw.BuildingPw;
import com.angelos.koinoxrhsta.def.pw.FlatPw;
import com.angelos.koinoxrhsta.def.pw.IssuerPw;
import com.angelos.koinoxrhsta.def.pw.OwnerPw;
import com.angelos.koinoxrhsta.def.pw.ParkingPw;
import com.angelos.koinoxrhsta.impl.enums.Sex;
import com.angelos.koinoxrhsta.impl.enums.Side;

@Transactional(rollbackFor = Exception.class)
@Service
public class Executable {

	
	BuildingPw buildingPw;
	FlatPw flatPw;
	OwnerPw ownerPw;
	ParkingPw parkingPw;
	BillPw billPw;
	IssuerPw issuerPw;
	Building building;
	Owner owner;
	Flat flat;
	Parking parking;
	
	public Executable(BuildingPw buildingPw, FlatPw flatPw, OwnerPw ownerPw, ParkingPw parkingPw, BillPw billPw, IssuerPw issuerPw,
			Building building, Owner owner,	Flat flat, Parking parking) {
		this.buildingPw = buildingPw;
		this.flatPw = flatPw;
		this.ownerPw = ownerPw;
		this.parkingPw = parkingPw;
		this.billPw = billPw;
		this.issuerPw = issuerPw;
		this.building = building;
		this.owner = owner;
		this.flat = flat;
		this.parking = parking;
	}



	public void execute() {

		
		building.setOwnershipMillis(1000);
		building.setAddressName("Timotheou");
		building.setAddressNo(33);
		building.setBuiltDate(LocalDate.of(1967, 10, 5));
		building.setFlatsTotal(10);
		building.setPostalCode(17800);
		building.setFloorsTotal(5);
		
		building = buildingPw.save(building);
		
//		BuildingKey key = new BuildingKey();
//		key.setBuildingId(1000022L);
//		Optional<Building> buildingOpt = buildingPw.findById(key);
//		Building building = buildingOpt.get();
//		System.out.println(building);
		


		
		owner.setName("loulou");
		owner.setSurname("kakia");
		owner.setBirthDate(LocalDate.of(1950, 5, 15));
		owner.setSex(Sex.FEMALE);

//		owner = ownerPw.save(owner);
//		System.out.println(owner);
		
		

		flat.setBuilding(building);
		flat.setOwner(owner);
		flat.setFlatName("D2");
		flat.setFloor(4);
		flat.setOwnershipMillis(75);
		flat = flatPw.save(flat);
		
		System.out.println(flat);
		
		
		parking.setArea(9);
		parking.setEntrance(Side.LEFT);
		parking.setBuilding(building);
		parking.setFlatId(flat.getFlatId());

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

package com.angelos.koinoxrhsta.impl.op;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.angelos.koinoxrhsta.KoinoxrhstaApplication;
import com.angelos.koinoxrhsta.def.pw.BillPw;
import com.angelos.koinoxrhsta.def.pw.BuildingPw;
import com.angelos.koinoxrhsta.def.pw.FlatPw;
import com.angelos.koinoxrhsta.def.pw.FlatSpecPw;
import com.angelos.koinoxrhsta.def.pw.IssuerPw;
import com.angelos.koinoxrhsta.def.pw.OwnerPw;
import com.angelos.koinoxrhsta.def.pw.ParkingPw;
import com.angelos.koinoxrhsta.impl.enums.Sex;
import com.angelos.koinoxrhsta.impl.enums.Side;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.FlatSpec;
import com.angelos.koinoxrhsta.impl.po.Owner;
import com.angelos.koinoxrhsta.impl.po.Parking;
import com.angelos.koinoxrhsta.impl.po.keys.BuildingKey;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TestCreateEntitiesGeneral {

	@Autowired
	BuildingPw buildingPw;
	@Autowired
	FlatPw flatPw;
	@Autowired
	OwnerPw ownerPw;
	@Autowired
	ParkingPw parkingPw;
	@Autowired
	BillPw billPw;
	@Autowired
	IssuerPw issuerPw;
	@Autowired
	FlatSpecPw flatSpecPw;

	@Test
	public void execute() {

		/**
		 *  Persist to DB
		 */
		Building building = new Building();
		building.setOwnershipMillis(1000);
		building.setAddressName("kalidromiou");
		building.setAddressNo(102);
		building.setBuiltDate(LocalDate.of(1989, 11, 5));
		building.setFlatsTotal(10);
		building.setPostalCode(16899);
		building.setFloorsTotal(5);

		building = buildingPw.save(building);

		Owner owner = new Owner();
		owner.setName("Koula");
		owner.setSurname("Lona");
		owner.setBirthDate(LocalDate.of(1980, 5, 17));
		owner.setSex(Sex.MALE);
		owner = ownerPw.save(owner);

		Flat flat = new Flat();
		flat.setBuilding(building);
		flat.setOwner(owner);
		flat.setFlatName("D2");
		flat.setFloor(4);
		flat.setOwnershipMillis(75);
		flat = flatPw.save(flat);
		
		FlatSpec flatSpec = new FlatSpec();
		flatSpec.setBuilding(building);
		flatSpec.setFlatId(flat.getFlatId());
		flatSpec.setTotalArea(100);
		flatSpec.setBedroomsNo(2);
		flatSpec.setBathroomsNo(1);
		flatSpec.setBalconiesNo(3);
		flatSpec.setBalconiesArea(20);
		flatSpec.setLivingroomsNo(1);
		flatSpec.setKitchensNo(1);
		flatSpec = flatSpecPw.save(flatSpec);

		Parking parking = new Parking();
		parking.setArea(9);
		parking.setEntrance(Side.BACK);
		parking.setBuilding(building);
		parking.setFlatId(flat.getFlatId());
		parking = parkingPw.save(parking);

		flat.setFlatSpec(flatSpec);
		flat.setFlatSpec(flatSpec);
		flat.setParking(parking);
		flat = flatPw.save(flat);
		
		/**
		 * Retrive from DB
		 */
		BuildingKey buildingKey = new BuildingKey();
		buildingKey.setBuildingId(building.getBuildingId());
		Building expectedBuilding = buildingPw.getReferenceById(buildingKey);
		
		FlatKey flatKey = new FlatKey();
		flatKey.setBuilding(expectedBuilding);
		flatKey.setFlatId(flat.getFlatId());
		Flat expectedFlat = flatPw.getReferenceById(flatKey);
		
		
		/**
		 * Assert equility
		 */
		assertThat(flat).usingRecursiveComparison().isEqualTo(expectedFlat);
	}
}
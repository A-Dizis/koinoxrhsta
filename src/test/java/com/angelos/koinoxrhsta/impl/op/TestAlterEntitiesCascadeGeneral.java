package com.angelos.koinoxrhsta.impl.op;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.angelos.koinoxrhsta.def.pw.BuildingPw;
import com.angelos.koinoxrhsta.def.pw.FlatPw;
import com.angelos.koinoxrhsta.def.pw.FlatSpecPw;
import com.angelos.koinoxrhsta.def.pw.OwnerPw;
import com.angelos.koinoxrhsta.impl.enums.Sex;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.FlatSpec;
import com.angelos.koinoxrhsta.impl.po.Owner;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;
import com.angelos.koinoxrhsta.impl.utils.TestRandomInfoUtility;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TestAlterEntitiesCascadeGeneral {

	@Autowired
	BuildingPw buildingPw;
	@Autowired
	FlatPw flatPw;
	@Autowired
	OwnerPw ownerPw;
	@Autowired
	FlatSpecPw flatSpecPw;

	@Test
	public void execute() {

		/**
		 *  Persist to DB
		 */
		Building building = new Building();
		building.setOwnershipMillis(1000);
		building.setAddressName(TestRandomInfoUtility.getStreetName());
		building.setAddressNo(TestRandomInfoUtility.getRandom().nextInt(1, 200));
		building.setBuiltDate(TestRandomInfoUtility.randomDate(LocalDate.of(1950, 1, 1), LocalDate.of(2000, 1, 1)));
		building.setFlatsTotal(10);
		building.setPostalCode(TestRandomInfoUtility.getRandom().nextInt(50000));
		building.setFloorsTotal(5);

		building = buildingPw.save(building);

		Owner owner = new Owner();
		owner.setName(TestRandomInfoUtility.getFirstName());
		owner.setSurname(TestRandomInfoUtility.getLastName());
		owner.setBirthDate(TestRandomInfoUtility.randomDate(LocalDate.of(1950, 1, 1), LocalDate.now()));
		owner.setSex(Sex.MALE);
		owner = ownerPw.save(owner);

		Flat flat = new Flat();
		flat.setBuildingId(building.getBuildingId());
		flat.setOwner(owner);
		flat.setFlatName("D2");
		flat.setFloor(4);
		flat.setOwnershipMillis(75);
		flat = flatPw.save(flat);
		
		FlatSpec flatSpec = new FlatSpec();
		flatSpec.setBuildingId(building.getBuildingId());
		flatSpec.setFlatId(flat.getFlatId());
		flatSpec.setTotalArea(100);
		flatSpec.setBedroomsNo(2);
		flatSpec.setBathroomsNo(1);
		flatSpec.setBalconiesNo(3);
		flatSpec.setBalconiesArea(20);
		flatSpec.setLivingroomsNo(1);
		flatSpec.setKitchensNo(1);
		flatSpec = flatSpecPw.save(flatSpec);

		FlatKey flatKey = new FlatKey();
		flatKey.setBuildingId(building.getBuildingId());
		flatKey.setFlatId(flat.getFlatId());
		Optional<Flat> flatRead = flatPw.findById(flatKey);

		flatRead.get().getFlatSpec().setBedroomsNo(100);
		flatPw.save(flatRead.get());
		
		/**
		 * Retrieve from DB. Check entities persisted by entities persisted ok.
		 */
		Flat expectedFlat = flatPw.getReferenceById(flatKey);
		
		
		/**
		 * Assert equality. In memory agrees with database data.
		 */
		System.out.println("ASSERTING ----------------" + assertThat(100).isEqualTo(expectedFlat.getFlatSpec().getBedroomsNo()));
	}
}
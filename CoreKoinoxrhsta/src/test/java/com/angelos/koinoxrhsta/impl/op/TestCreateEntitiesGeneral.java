package com.angelos.koinoxrhsta.impl.op;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.angelos.koinoxrhsta.def.infrastructure.Operation;
import com.angelos.koinoxrhsta.impl.enums.Sex;
import com.angelos.koinoxrhsta.impl.enums.Side;
import com.angelos.koinoxrhsta.impl.exception.RepositoryException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterFactory;
import com.angelos.koinoxrhsta.impl.po.Bill;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.FlatSpec;
import com.angelos.koinoxrhsta.impl.po.Issuer;
import com.angelos.koinoxrhsta.impl.po.Owner;
import com.angelos.koinoxrhsta.impl.po.Parking;
import com.angelos.koinoxrhsta.impl.po.Warehouse;
import com.angelos.koinoxrhsta.impl.po.keys.BillKey;
import com.angelos.koinoxrhsta.impl.po.keys.BuildingKey;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;
import com.angelos.koinoxrhsta.impl.po.keys.FlatSpecKey;
import com.angelos.koinoxrhsta.impl.po.keys.IssuerKey;
import com.angelos.koinoxrhsta.impl.po.keys.OwnerKey;
import com.angelos.koinoxrhsta.impl.po.keys.ParkingKey;
import com.angelos.koinoxrhsta.impl.po.keys.WarehouseKey;
import com.angelos.koinoxrhsta.impl.utils.TestRandomInfoUtility;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TestCreateEntitiesGeneral extends Operation {

	GenericPersisterFactory gpf;

	private GenericPersister<Building, BuildingKey> gpBuilding;
	private GenericPersister<Flat, FlatKey> gpFlat;
	private GenericPersister<Owner, OwnerKey> gpOwner;
	private GenericPersister<Parking, ParkingKey> gpParking;
	private GenericPersister<Bill, BillKey> gpBill;
	private GenericPersister<Issuer, IssuerKey> gpIssuer;
	private GenericPersister<FlatSpec, FlatSpecKey> gpFlatSpec;
	private GenericPersister<Warehouse, WarehouseKey> gpWarehouse;

	@Autowired
	public void injectDependencies(GenericPersisterFactory gpf) throws RepositoryException {
		this.gpf = gpf;

		gpBuilding = gpf.create(Building.class); 
		gpFlat = gpf.create(Flat.class); 
		gpOwner = gpf.create(Owner.class); 
		gpParking = gpf.create(Parking.class); 
		gpBill = gpf.create(Bill.class); 
		gpIssuer = gpf.create(Issuer.class); 
		gpFlatSpec = gpf.create(FlatSpec.class); 
		gpWarehouse = gpf.create(Warehouse.class); 
	};

	@Test
	public void execute() {

		/**
		 * Persist to DB
		 */
		/**
		 * Persist to DB
		 */
		Building building = new Building();
		building.setOwnershipMillis(1000);
		building.setAddressName(TestRandomInfoUtility.getStreetName());
		building.setAddressNo(TestRandomInfoUtility.getRandom().nextInt(1, 200));
		building.setBuiltDate(TestRandomInfoUtility.randomDate(LocalDate.of(1950, 1, 1), LocalDate.of(2000, 1, 1)));
		building.setFlatsTotal(10);
		building.setPostalCode(TestRandomInfoUtility.getRandom().nextInt(50000));
		building.setFloorsTotal(5);

		building = gpBuilding.save(building);

		Owner owner = new Owner();
		owner.setName(TestRandomInfoUtility.getFirstName());
		owner.setSurname(TestRandomInfoUtility.getLastName());
		owner.setBirthDate(TestRandomInfoUtility.randomDate(LocalDate.of(1950, 1, 1), LocalDate.now()));
		owner.setSex(Sex.MALE);
		owner = gpOwner.save(owner);

		Flat flat = new Flat();
		flat.setBuildingId(building.getBuildingId());
		flat.setOwner(owner);
		flat.setFlatName("D2");
		flat.setFloor(4);
		flat.setOwnershipMillis(75);
		flat = gpFlat.save(flat);

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
		flatSpec = gpFlatSpec.save(flatSpec);

		Parking parking = new Parking();
		parking.setArea(9);
		parking.setEntrance(Side.BACK);
		parking.setBuildingId(building.getBuildingId());
		parking.setFlatId(flat.getFlatId());
		parking = gpParking.save(parking);

		Warehouse warehouse = new Warehouse();
		warehouse.setBuildingId(building.getBuildingId());
		warehouse.setFlatId(flat.getFlatId());
		warehouse.setName("D2");
		warehouse.setArea(10);
		warehouse = gpWarehouse.save(warehouse);

		/**
		 * They are store in database an will be read correctly should a read operation
		 * happens
		 */
		flat.setFlatSpec(flatSpec);
		flat.setWarehouse(warehouse);
		flat.setParking(parking);

		Issuer issuer = new Issuer();
		issuer.setName("DEH");
		issuer.setServiceDescription("Paroxos Ilektrikou Reymatos");
		issuer = gpIssuer.save(issuer);

		Bill bill = new Bill();
		bill.setFlatId(flat.getFlatId());
		bill.setBuildingId(building.getBuildingId());
		bill.setIssuer(issuer);
		bill.setAmountCharged(BigDecimal.valueOf(TestRandomInfoUtility.getRandom().nextDouble(1000, 2000)));
		bill.setPaid(true);
		bill = gpBill.save(bill);

		/**
		 * Retrive from DB. Correctly fetches all associated entities when reading
		 * operation happens.
		 */
		Building expectedBuilding = new Building();
		expectedBuilding.setKey(building.getKey());
		try {
			expectedBuilding = gpBuilding.read(expectedBuilding);
			System.out.println("Comparing keys...");
			System.out.println(expectedBuilding.getKey());
			System.out.println(building.getKey());
		} catch (IllegalArgumentException | SecurityException e) {
			throw new RuntimeException(e);
		}

		FlatKey flatKey = new FlatKey();
		flatKey.setBuildingId(building.getBuildingId());
		flatKey.setFlatId(flat.getFlatId());
		Flat expectedFlat = new Flat();
		expectedFlat.setKey(flatKey);
		expectedFlat = gpFlat.read(expectedFlat);

		/**
		 * Assert equality. In memory agrees with database data.
		 */
		System.out.println("ASSERTING ----------------"
				+ assertThat(building).usingRecursiveComparison().isEqualTo(expectedBuilding));
		System.out.println(
				"ASSERTING ----------------" + assertThat(flat).usingRecursiveComparison().isEqualTo(expectedFlat));
	}
}
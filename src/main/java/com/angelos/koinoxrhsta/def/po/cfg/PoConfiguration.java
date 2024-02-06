package com.angelos.koinoxrhsta.def.po.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.angelos.koinoxrhsta.def.po.Bill;
import com.angelos.koinoxrhsta.def.po.Building;
import com.angelos.koinoxrhsta.def.po.Flat;
import com.angelos.koinoxrhsta.def.po.FlatSpec;
import com.angelos.koinoxrhsta.def.po.Issuer;
import com.angelos.koinoxrhsta.def.po.Owner;
import com.angelos.koinoxrhsta.def.po.Parking;
import com.angelos.koinoxrhsta.def.po.Warehouse;
import com.angelos.koinoxrhsta.impl.po.BillImpl;
import com.angelos.koinoxrhsta.impl.po.BuildingImpl;
import com.angelos.koinoxrhsta.impl.po.FlatImpl;
import com.angelos.koinoxrhsta.impl.po.FlatSpecImpl;
import com.angelos.koinoxrhsta.impl.po.IssuerImpl;
import com.angelos.koinoxrhsta.impl.po.OwnerImpl;
import com.angelos.koinoxrhsta.impl.po.ParkingImpl;
import com.angelos.koinoxrhsta.impl.po.WarehouseImpl;

@Configuration
public class PoConfiguration {

	@Bean
	Bill Bill() {
		return new BillImpl();
	}
	
	@Bean
	Building Building() {
		return new BuildingImpl();
	}
	
	@Bean
	Flat Flat() {
		return new FlatImpl();
	}
	
	@Bean
	FlatSpec FlatSpec() {
		return new FlatSpecImpl();
	}
	
	@Bean
	Issuer Issuer() {
		return new IssuerImpl();
	}
	
	@Bean
	Owner Owner() {
		return new OwnerImpl();
	}
	
	@Bean
	Parking Parking() {
		return new ParkingImpl();
	}
	
	@Bean
	Warehouse Warehouse() {
		return new WarehouseImpl();
	}
	
}

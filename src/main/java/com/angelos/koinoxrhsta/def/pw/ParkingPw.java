package com.angelos.koinoxrhsta.def.pw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angelos.koinoxrhsta.def.po.Parking;
import com.angelos.koinoxrhsta.impl.po.keys.ParkingKey;

@Repository
public interface ParkingPw extends JpaRepository<Parking, ParkingKey>{

}

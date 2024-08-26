package com.angelos.koinoxrhsta.def.pw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.angelos.koinoxrhsta.impl.po.Parking;
import com.angelos.koinoxrhsta.impl.po.keys.ParkingKey;

@Deprecated
@CrossOrigin
@Repository
public interface ParkingPw extends JpaRepository<Parking, ParkingKey>{

}

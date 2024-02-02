package com.angelos.koinoxrhsta.def.pw;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.keys.BuildingKey;

public interface BuildingPw extends JpaRepository<Building, BuildingKey> {

}

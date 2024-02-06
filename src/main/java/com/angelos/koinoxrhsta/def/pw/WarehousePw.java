package com.angelos.koinoxrhsta.def.pw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angelos.koinoxrhsta.impl.po.Warehouse;
import com.angelos.koinoxrhsta.impl.po.keys.WarehouseKey;

@Repository
public interface WarehousePw extends JpaRepository<Warehouse, WarehouseKey>{

}

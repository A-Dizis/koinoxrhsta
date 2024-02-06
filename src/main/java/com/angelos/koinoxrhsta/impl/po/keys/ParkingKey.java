package com.angelos.koinoxrhsta.impl.po.keys;

import com.angelos.koinoxrhsta.impl.po.Building;

import lombok.Data;

@Data
public class ParkingKey {

    private Building building;

    private Long flatId;
	
}

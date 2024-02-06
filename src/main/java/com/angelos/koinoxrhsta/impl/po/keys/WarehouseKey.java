package com.angelos.koinoxrhsta.impl.po.keys;

import com.angelos.koinoxrhsta.impl.po.Building;

import lombok.Data;

@Data
public class WarehouseKey {
	
    private Building building;

    private Long flatId;
    
}

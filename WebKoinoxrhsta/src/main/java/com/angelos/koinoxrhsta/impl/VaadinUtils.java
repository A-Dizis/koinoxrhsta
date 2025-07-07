package com.angelos.koinoxrhsta.impl;

import org.springframework.stereotype.Service;

import com.vaadin.flow.component.grid.Grid;

@Service
public class VaadinUtils {
    
    public static void removeColumnsById(Grid<?> grid, String ... columnIds) {
          for (String columnId : columnIds) {
            try {
                grid.removeColumnByKey(columnId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            
        }
    }
}

package com.angelos.koinoxrhsta.web;

import com.angelos.koinoxrhsta.impl.po.Flat;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("flats") // localhost:8080/flats
public class FlatsLayout extends VerticalLayout {

  // Autowire a Spring @Service
  public FlatsLayout() {

    // Instantiate Vaadin data grid component
    Grid<Flat> grid = new Grid<>(Flat.class);
    
    FlatAddLayout lay = new FlatAddLayout();

    // Add components to the layout to show them
    add(
        new H1("All FLATS"),
        grid,
        lay);
  }
}
package com.angelos.koinoxrhsta.web;

import com.angelos.koinoxrhsta.impl.po.Flat;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("flats") // localhost:8080/flats
public class Sample extends VerticalLayout {

  // Autowire a Spring @Service
  public Sample() {

    // Instantiate Vaadin data grid component
    Grid<Flat> grid = new Grid<>(Flat.class);

    // Define columns
    grid.addColumn(Flat::getFlatId).setHeader("FlatIIII");
    grid.addColumn(Flat::getFlatName).setHeader("FlatName");
    grid.addColumn(Flat::getBuildingId).setHeader("BuildingId");

    // Add components to the layout to show them
    add(
        new H1("All FLATS"),
        grid);
  }
}
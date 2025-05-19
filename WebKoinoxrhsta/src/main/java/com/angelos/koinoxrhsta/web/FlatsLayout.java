package com.angelos.koinoxrhsta.web;

import org.springframework.stereotype.Component;

import com.angelos.koinoxrhsta.impl.exception.RepositoryException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterFactory;
import com.angelos.koinoxrhsta.impl.op.DeleteFlatOperation;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Component
@Route("flats") // localhost:8080/flats
public class FlatsLayout extends VerticalLayout {

  GenericPersisterFactory gpf;

  DeleteFlatOperation deleteFlatOp;

  public FlatsLayout(GenericPersisterFactory gpf, DeleteFlatOperation deleteFlatOp) throws RepositoryException {

    this.gpf = gpf;
    this.deleteFlatOp = deleteFlatOp;

    GenericPersister<Flat, FlatKey> gpFlat = gpf.create(Flat.class);

    // Instantiate Vaadin data grid component
    Grid<Flat> grid = new Grid<>(Flat.class);
    grid.setItems(gpFlat.findAll());
    grid.addItemClickListener(event -> {
        deleteFlatOp.setFlat(event.getItem());
        deleteFlatOp.execute();
    });
    grid.getDataProvider().refreshAll();


    // Add components to the layout to show them
    add(new H1("All FLATS"), grid);
  }
}
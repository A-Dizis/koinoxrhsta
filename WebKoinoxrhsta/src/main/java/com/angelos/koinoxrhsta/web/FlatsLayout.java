package com.angelos.koinoxrhsta.web;

import com.angelos.koinoxrhsta.def.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.impl.exception.DataException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterFactory;
import com.angelos.koinoxrhsta.impl.op.DeleteFlatOperation;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class FlatsLayout extends VerticalLayout {

    Flat selected = null;

    GenericPersisterFactory gpf;

    DeleteFlatOperation deleteFlatOp;

    public FlatsLayout(GenericPersisterFactory gpf, DeleteFlatOperation deleteFlatOp) throws DataException {

        this.gpf = gpf;
        this.deleteFlatOp = deleteFlatOp;

        GenericPersister<Flat, FlatKey> gpFlat = gpf.create(Flat.class);

        // Instantiate Vaadin data grid component
        Grid<Flat> grid = new Grid<>(Flat.class);
        ListDataProvider<Flat> flatsProvider = new ListDataProvider<>(gpFlat.findAll());
        grid.setItems(flatsProvider);
        grid.addItemClickListener(event -> {
            selected = event.getItem();
        });

        Button deleteFlatButton = new Button("Delete selected");
        deleteFlatButton.addClickListener(e -> {
            try {
                selected = gpf.create(Flat.class).read(selected);
                deleteFlatOp.setFlat(selected);
                deleteFlatOp.execute();
                flatsProvider.getItems().remove(selected);
                grid.getDataProvider().refreshAll();
            } catch (DataException e1) {
                throw new RuntimeException();
            }
            grid.getDataProvider().refreshAll();
        });

        // Add components to the layout to show them
        add(new H1("All FLATS"), grid, deleteFlatButton);
    }
}
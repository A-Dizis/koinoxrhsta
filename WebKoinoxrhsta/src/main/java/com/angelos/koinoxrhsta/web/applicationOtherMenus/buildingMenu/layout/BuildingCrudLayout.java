package com.angelos.koinoxrhsta.web.applicationOtherMenus.buildingMenu.layout;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.util.CollectionUtils;
import org.vaadin.crudui.crud.AddOperationListener;
import org.vaadin.crudui.crud.DeleteOperationListener;
import org.vaadin.crudui.crud.FindAllCrudOperationListener;
import org.vaadin.crudui.crud.UpdateOperationListener;
import org.vaadin.crudui.crud.impl.GridCrud;

import com.angelos.koinoxrhsta.def.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.impl.VaadinUtils;
import com.angelos.koinoxrhsta.impl.exception.DataException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterFactory;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.keys.BuildingKey;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;
import com.angelos.koinoxrhsta.impl.query.FindFlatsOfBuildingQuery;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class BuildingCrudLayout extends Composite<Component> {

    VerticalLayout pageLayout;

    GenericPersisterFactory gpf;
    GenericPersister<Building, BuildingKey> gpBuilding;
    GenericPersister<Flat, FlatKey> gpFlat;

    FindFlatsOfBuildingQuery query;
    
    Optional<Building> selected;
    Optional<Flat> selectedFlat;



    BuildingCrudLayout (GenericPersisterFactory gpf, FindFlatsOfBuildingQuery query) throws DataException {
        this.gpf = gpf;
        gpBuilding = gpf.create(Building.class);
        gpFlat = gpf.create(Flat.class);
        this.query = query;
    }
    
    @Override
    protected Component initContent() {
        pageLayout = new VerticalLayout();

        GridCrud<Building> buildingSearchLayout = new GridCrud<>(Building.class);
        buildingSearchLayout.setFindAllOperation(findAllBuildingsOperationListener());
        buildingSearchLayout.setUpdateOperation(updateOperationListener());
        buildingSearchLayout.setAddOperationVisible(false);
        buildingSearchLayout.setDeleteOperationVisible(false);
        configureBuildingGrid(buildingSearchLayout.getGrid());

        VerticalLayout drawArea = new VerticalLayout();

        HorizontalLayout selectedBuildingActionButtonsPanel = new HorizontalLayout();
        selectedBuildingActionButtonsPanel.add(showFlatsButton(drawArea));

        pageLayout.add(new H2("Building Managment"), buildingSearchLayout, selectedBuildingActionButtonsPanel, drawArea);

        return pageLayout;
    }
   
    FindAllCrudOperationListener<Building> findAllBuildingsOperationListener() {
        return new FindAllCrudOperationListener<Building>() {

            @Override
            public Collection<Building> findAll() {
              return gpBuilding.findAll();
            }
            
        };
    }

    UpdateOperationListener<Building> updateOperationListener() {
        return new UpdateOperationListener<Building>() {

            @Override
            public Building perform(Building domainObject) {
                try {
                    return gpBuilding.update(domainObject);
                } catch (DataException e) {
                    throw new RuntimeException(e);
                }
            }
            
        };
    }

    AddOperationListener<Building> addOperationListener() {

        return new AddOperationListener<Building>() {

            @Override
            public Building perform(Building domainObject) {
                try {
                    return gpBuilding.save(domainObject);
                } catch (DataException e) {
                    throw new RuntimeException(e);
                }
            }
            
        };
    }

    void configureBuildingGrid(Grid<Building> grid) {
        VaadinUtils.removeColumnsById(grid, "lastVersion", "key");
        grid.setSelectionMode(SelectionMode.SINGLE);
        grid.addSelectionListener(e -> {
            selected = e.getFirstSelectedItem();
        });
    }

    @SuppressWarnings("unchecked")
    Button showFlatsButton(VerticalLayout drawArea) {
        Button button = new Button("Show flats");
        button.addClickListener(e -> {
            if(selected.isPresent()){
                query.setBuilding(selected.get());
                query.execute();
                List<Flat> resultList = query.getResultList();
                if(!CollectionUtils.isEmpty(resultList)) {
                    GridCrud<Flat> flatCrud = new GridCrud<>(Flat.class);
                    flatCrud.setFindAllOperation(findAllFlatsOperationListener(selected));
                    flatCrud.setAddOperationVisible(false);
                    flatCrud.setUpdateOperationVisible(false);
                    flatCrud.setDeleteOperation(deleteFlatOperationListener());
                    configureFlatGrid(flatCrud.getGrid());
                    


                    drawArea.add(new H2("Flats of Building with ID: " + selected.get().getBuildingId()), flatCrud);
                    return;
                }
                drawArea.removeAll();
            }
        });
        return button;
    }
    
    void configureFlatGrid(Grid<Flat> grid) {
        VaadinUtils.removeColumnsById(grid,  "buildingId", "flatSpec", "owner", "key", "parking", "warehouse", "lastVersion");
        grid.setSelectionMode(SelectionMode.SINGLE);
        grid.addSelectionListener(o -> {selectedFlat = o.getFirstSelectedItem();});

    }

    FindAllCrudOperationListener<Flat> findAllFlatsOperationListener(Optional<Building> selected) {
        return new FindAllCrudOperationListener<Flat>() {

            @Override
            public Collection<Flat> findAll() {
                query.setBuilding(selected.get());
                query.execute();
                return query.getResultList();
            }
        };
    }

    DeleteOperationListener<Flat> deleteFlatOperationListener() {
        return new DeleteOperationListener<Flat>() {

            @Override
            public void perform(Flat domainObject) {
               try {

                    gpFlat.delete(domainObject);
               } catch (DataException e) {
                    throw new RuntimeException(e);
               }
            }
        };
    }

}

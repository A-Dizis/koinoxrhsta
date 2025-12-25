package com.angelos.koinoxrhsta.web.applicationOtherMenus.buildingMenu.layout;

import java.util.ArrayList;
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
import com.angelos.koinoxrhsta.impl.op.DeleteFlatOperation;
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
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class BuildingCrudLayout extends Composite<Component> {

    VerticalLayout pageLayout = null;

    VerticalLayout drawArea = null;

    GenericPersisterFactory gpf;
    GenericPersister<Building, BuildingKey> gpBuilding;
    GenericPersister<Flat, FlatKey> gpFlat;

    DeleteFlatOperation deleteOp;

    FindFlatsOfBuildingQuery query;
    
    DeleteFlatOperation deleteFlatOp;

    Optional<Building> selectedBuilding;
    Optional<Flat> selectedFlat;

    BuildingCrudLayout (GenericPersisterFactory gpf, FindFlatsOfBuildingQuery query, DeleteFlatOperation deleteFlatOp) throws DataException {
        this.gpf = gpf;
        gpBuilding = gpf.create(Building.class);
        gpFlat = gpf.create(Flat.class);
        this.query = query;
        this.deleteFlatOp = deleteFlatOp;
    }
    
    @Override
    protected Component initContent() {
        pageLayout = new VerticalLayout();

        drawArea = new VerticalLayout();

        GridCrud<Building> buildingSearchLayout = new GridCrud<>(Building.class);
        buildingSearchLayout.setFindAllOperation(findAllBuildingsListener());
        buildingSearchLayout.setUpdateOperation(updateBuildingListener());
        buildingSearchLayout.setDeleteOperation(deleteBuildingListener());
        buildingSearchLayout.setDeletedMessage(null);
        buildingSearchLayout.setAddOperationVisible(false);
        configureBuildingGrid(buildingSearchLayout.getGrid());

        HorizontalLayout selectedBuildingActionButtonsPanel = new HorizontalLayout();
        selectedBuildingActionButtonsPanel.add(showFlatsButton());

        pageLayout.add(new H2("Building Managment"), buildingSearchLayout, selectedBuildingActionButtonsPanel, drawArea);

        return pageLayout;
    }
   
    FindAllCrudOperationListener<Building> findAllBuildingsListener() {
        return new FindAllCrudOperationListener<Building>() {

            @Override
            public Collection<Building> findAll() {
              return gpBuilding.findAll();
            }
            
        };
    }

    UpdateOperationListener<Building> updateBuildingListener() {
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

    @SuppressWarnings("unchecked")
    DeleteOperationListener<Building> deleteBuildingListener() {
        return new DeleteOperationListener<Building>() {

            @Override
            public void perform(Building domainObject) {
                resetDrawArea();
                setDrawAreaAlignment(JustifyContentMode.CENTER);

                query.setBuilding(domainObject);
                query.execute();
                List<Flat> resultList = query.getResultList();
                H3 message = null;
                if(!CollectionUtils.isEmpty(resultList)) {
                    message = new H3("Operation cannot be completed. Delete Flats of the building first.");
                    message.getStyle().set("color", "red");
                    drawArea.add(message);
                    return;
                }
                try {
                    gpBuilding.delete(domainObject);
                } catch (DataException e) {
                    throw new RuntimeException(e);
                }
                message = new H3("Operation completed successfully. Building: " + domainObject.getBuildingId() + " deleted.");
                message.getStyle().set("color", "green");
                drawArea.add(message);
            }
        };
    }

    AddOperationListener<Building> addBuildingListener() {

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
            selectedBuilding = e.getFirstSelectedItem();
        });
    }

    @SuppressWarnings("unchecked")
    Button showFlatsButton() {

        Button button = new Button("Show flats");
        button.addClickListener(e -> {
            resetDrawArea();

            if(selectedBuilding.isPresent()){
                query.setBuilding(selectedBuilding.get());
                query.execute();
                List<Flat> resultList = query.getResultList();
                if(!CollectionUtils.isEmpty(resultList)) {
                    GridCrud<Flat> flatCrud = new GridCrud<>(Flat.class);
                    flatCrud.setFindAllOperation(findAllFlatsListener(selectedBuilding));
                    flatCrud.setAddOperationVisible(false);
                    flatCrud.setUpdateOperationVisible(false);
                    flatCrud.setDeleteOperation(deleteFlatListener());
                    flatCrud.setDeletedMessage("Flat successfully deleted.");
                    configureFlatGrid(flatCrud.getGrid());
                    
                    drawArea.add(new H2("Flats of Building with ID: " + selectedBuilding.get().getBuildingId()), flatCrud);
                    setDrawAreaAlignment(JustifyContentMode.CENTER);
                }
            }
        });
        return button;
    }
    
    void configureFlatGrid(Grid<Flat> grid) {
        VaadinUtils.removeColumnsById(grid,  "buildingId", "flatSpec", "owner", "key", "parking", "warehouse", "lastVersion");
        grid.setSelectionMode(SelectionMode.SINGLE);
        grid.addSelectionListener(e -> {
            selectedFlat = e.getFirstSelectedItem();
        });
    }
    

    FindAllCrudOperationListener<Flat> findAllFlatsListener(Optional<Building> selected) {
        return new FindAllCrudOperationListener<Flat>() {

            @SuppressWarnings("unchecked")
            @Override
            public Collection<Flat> findAll() {
                query.setBuilding(selected.get());
                query.execute();
                
                List<Flat> result = query.getResultList();
                if(result != null && !result.isEmpty()) {
                    return result;
                }
                
                return new ArrayList<>();
            }
        };
    }

    DeleteOperationListener<Flat> deleteFlatOperationListener() {

        return new DeleteOperationListener<Flat>() {

            @Override
            public void perform(Flat domainObject) {
                try {
                deleteFlatOp.setFlat(domainObject);
                deleteFlatOp.execute();
                } catch (DataException e) {
                    e.printStackTrace();
                }
            }           
        };
    }

    void resetDrawArea() {
        drawArea.removeAll();
        drawArea.setJustifyContentMode(JustifyContentMode.START);
    }

    void setDrawAreaAlignment(JustifyContentMode mode) {
        drawArea.setJustifyContentMode(mode);
    }
}

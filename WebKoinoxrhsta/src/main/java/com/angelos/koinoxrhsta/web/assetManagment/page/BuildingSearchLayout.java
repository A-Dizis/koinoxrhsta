package com.angelos.koinoxrhsta.web.assetManagment.page;

import java.util.Collection;

import org.vaadin.crudui.crud.AddOperationListener;
import org.vaadin.crudui.crud.FindAllCrudOperationListener;
import org.vaadin.crudui.crud.UpdateOperationListener;
import org.vaadin.crudui.crud.impl.GridCrud;

import com.angelos.koinoxrhsta.def.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.impl.VaadinUtils;
import com.angelos.koinoxrhsta.impl.exception.DataException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterFactory;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.keys.BuildingKey;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class BuildingSearchLayout extends Composite<Component> {

    GenericPersisterFactory gpf;
    GenericPersister<Building, BuildingKey> gpBuilding;

    BuildingSearchLayout (GenericPersisterFactory gpf) throws DataException {
        this.gpf = gpf;
        gpBuilding = gpf.create(Building.class);
    }
    
    @Override
    protected Component initContent() {
        VerticalLayout pageLayout = new VerticalLayout();

        GridCrud<Building> buildingSearchLayout = new GridCrud<>(Building.class);
        buildingSearchLayout.setFindAllOperation(findAllCrudOperationListener());
        buildingSearchLayout.setAddOperation(null);
        buildingSearchLayout.setUpdateOperation(updateOperationListener());
        buildingSearchLayout.setDeleteOperationVisible(false);
        VaadinUtils.removeColumnsById(buildingSearchLayout.getGrid(), "buildingId", "lastVersion", "key");

        pageLayout.add(buildingSearchLayout);

        return pageLayout;
    }

    FindAllCrudOperationListener<Building> findAllCrudOperationListener() {
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

}

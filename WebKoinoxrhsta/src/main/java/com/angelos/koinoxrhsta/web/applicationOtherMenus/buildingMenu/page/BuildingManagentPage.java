package com.angelos.koinoxrhsta.web.applicationOtherMenus.buildingMenu.page;

import com.angelos.koinoxrhsta.web.applicationOtherMenus.buildingMenu.layout.BuildingCrudLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class BuildingManagentPage extends Composite<Component> {
    
    BuildingCrudLayout buildingCrudLayout;

    BuildingManagentPage (BuildingCrudLayout buildingCrudLayout) {
        this.buildingCrudLayout = buildingCrudLayout;
    }

    @Override
    protected Component initContent() {
        VerticalLayout pageLayout = new VerticalLayout();

        H1 pageHeader = new H1("General Buildings Management");

        pageLayout.getStyle().set("align-items", "center");
        pageLayout.add(pageHeader, buildingCrudLayout);

        return pageLayout;
    }

}

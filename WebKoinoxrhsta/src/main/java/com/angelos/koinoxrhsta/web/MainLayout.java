package com.angelos.koinoxrhsta.web;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainLayout extends HorizontalLayout {

    MainLayout(){
        Button newBuildingButton = new Button("Νέο κτήριο");
        add(newBuildingButton);
    }

}

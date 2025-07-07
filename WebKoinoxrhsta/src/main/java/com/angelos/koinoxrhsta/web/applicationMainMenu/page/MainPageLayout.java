package com.angelos.koinoxrhsta.web.applicationMainMenu.page;

import com.angelos.koinoxrhsta.web.assetManagment.page.BuildingSearchLayout;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class MainPageLayout extends Composite<Component> {
    
    @Override
    protected Component initContent() {

        HorizontalLayout navigationLayout = new HorizontalLayout();
        navigationLayout.add(buildingPageButton());
        navigationLayout.add(new Anchor("https://vaadin.com", new Button("Visit vaadin.com")));
        navigationLayout.add(new Anchor("https://vaadin.com", new Button("Visit vaadin.com")));
        navigationLayout.add(new Anchor("https://vaadin.com", new Button("Visit vaadin.com")));
        return navigationLayout;
    }
    
    Button buildingPageButton() {
        Button bt = new Button("Buildings");
        bt.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {

            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                RouteConfiguration.forSessionScope().setRoute("/Buildings", BuildingSearchLayout.class);
                UI.getCurrent().navigate("/Buildings");;
            }
            
        });

        return bt;

    }
}

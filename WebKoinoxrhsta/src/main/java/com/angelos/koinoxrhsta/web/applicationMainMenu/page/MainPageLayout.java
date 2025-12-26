package com.angelos.koinoxrhsta.web.applicationMainMenu.page;

import java.util.List;

import com.angelos.koinoxrhsta.impl.exception.DataException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterFactory;
import com.angelos.koinoxrhsta.impl.po.Page;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import jakarta.annotation.security.RolesAllowed;

@UIScope
@SpringComponent
@Route("")
@RolesAllowed({"SUPER_USER","ADMIN"})
public class MainPageLayout extends Composite<Component> {

    public static boolean initialized = false;

    private List<Page> allPages;

    MainPageLayout(GenericPersisterFactory gpf) throws DataException {
        allPages = gpf.create(Page.class).findAll();
    }
    
    @Override
    protected Component initContent() {

        HorizontalLayout navigationLayout = new HorizontalLayout();
        navigationLayout.getStyle().set("padding", "20px");
        for (Page page : allPages) {

            navigationLayout.add(createPageButton(page));
            if(!initialized) {
                addPageToRouteForTheSession(page);

            }
        }
        initialized = true;
  
        return navigationLayout;
    }

    Button createPageButton(Page page) {
        Button bt = new Button(page.getName());
        bt.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                UI.getCurrent().navigate(page.getRelativeUrl());
            }
        });
        return bt;
    }

    @SuppressWarnings("unchecked")
    void addPageToRouteForTheSession(Page page) {
        try {
            RouteConfiguration.forSessionScope().setRoute(page.getRelativeUrl(), (Class<? extends Component>)Class.forName(page.getClasspath()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

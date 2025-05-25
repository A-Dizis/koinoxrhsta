package com.angelos.koinoxrhsta.web;

import com.angelos.koinoxrhsta.impl.po.Flat;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class FlatAddLayout extends VerticalLayout {
    
    Flat flat;

    FlatAddLayout() {
        TextArea flatName = new TextArea("Flat name");

        Button button = new Button("Save");

        button.addClickListener(e -> Notification.show("probably born ready"));

        add(flatName, button);
    }
}

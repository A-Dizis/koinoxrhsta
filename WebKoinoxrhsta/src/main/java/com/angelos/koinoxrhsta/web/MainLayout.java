package com.angelos.koinoxrhsta.web;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainLayout extends HorizontalLayout {

    MainLayout() {
        LoginForm loginForm = new LoginForm();
        add(loginForm);
    }

}

package com.angelos.koinoxrhsta.web;

import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@PreserveOnRefresh
@Route("")
public class MainLayout extends HorizontalLayout {

    FlatsLayout flatsLayout;

    MainLayout(FlatsLayout flatsLayout) {

        this.flatsLayout = flatsLayout;

        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener(event -> {
                checkLogin(event);
        });
        add(loginForm);
    }


    void checkLogin(LoginEvent event) {
        String password = event.getPassword();
        String username = event.getUsername();

        if(username.equals("admin") && password.equals("pass")) {
            this.removeAll();
            this.add(flatsLayout);
            return;
        }
        event.getSource().setEnabled(true);

    }
}

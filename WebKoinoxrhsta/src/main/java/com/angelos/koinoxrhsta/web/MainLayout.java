package com.angelos.koinoxrhsta.web;

import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainLayout extends HorizontalLayout {

    MainLayout() {
        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener(this::checkLogin);
        add(loginForm);
    }


    void checkLogin(LoginEvent event) {
        String password = event.getPassword();
        String username = event.getUsername();

        if(username.equals("admin") && password.equals("pass")) {
            this.removeAll();
            this.add(new Text("Hello " + username + " your pass is " + password));
            return;
        }
        event.getSource().setEnabled(true);

    }
}

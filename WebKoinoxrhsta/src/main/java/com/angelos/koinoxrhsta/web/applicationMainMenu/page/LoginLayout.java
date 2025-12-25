package com.angelos.koinoxrhsta.web.applicationMainMenu.page;

import com.angelos.koinoxrhsta.web.FlatsLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@PreserveOnRefresh
@Route("/login")
public class LoginLayout extends Composite<Component> {

    public static boolean initialized;

    FlatsLayout flatsLayout;

    @Override
    protected Component initContent() {
        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener(event -> {
                checkLogin(event);
        });

        VerticalLayout loginLayout = new VerticalLayout(loginForm);
        loginLayout.setAlignItems(Alignment.CENTER);

        return loginLayout;
    }


    void checkLogin(LoginEvent event) {
        String password = event.getPassword();
        String username = event.getUsername();

        if(username.equals("admin") && password.equals("pass")) {

            if(!initialized) {
                RouteConfiguration.forSessionScope().setRoute("/MainMenu", MainPageLayout.class);
            }

            UI.getCurrent().navigate("/MainMenu");
            initialized = true;
        }
        event.getSource().setEnabled(true);

    }
}

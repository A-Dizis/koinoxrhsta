package com.angelos.koinoxrhsta.web.applicationMainMenu.page;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@PreserveOnRefresh
@Route("login") 
@PageTitle("Login")
@AnonymousAllowed
public class LoginLayout extends VerticalLayout implements BeforeEnterListener {

    private final LoginForm login = new LoginForm();

    public static boolean initialized;

    public LoginLayout() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        

        // This ensures the form submits a standard POST request to Spring Security
        login.setAction("login");

        add(new H1("Koinoxrhsta App"), login);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
       // Inform the user if an error occurred during login
        if (event.getLocation()
            .getQueryParameters()
            .getParameters()
            .containsKey("error")) {
            login.setError(true);
        }
    }
}

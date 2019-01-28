package com.vaadin.flow.component.login.examples;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.router.Route;

@Route(value = "")
public class Home extends AbstractView {

    private LoginForm loginForm = new LoginForm();

    public Home() {
        init(loginForm);
    }

}

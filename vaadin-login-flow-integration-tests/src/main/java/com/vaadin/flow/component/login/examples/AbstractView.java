package com.vaadin.flow.component.login.examples;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;

public abstract class AbstractView extends Div implements HasUrlParameter<String> {

    private AbstractLogin login;

    public AbstractView() {
        this.setSizeFull();
    }

    public void init(AbstractLogin login) {
        this.login = login;
        Notification notification = new Notification("", 15000, Notification.Position.MIDDLE);

        login.addForgotPasswordListener(e -> {
            notification.setText("Forgot password button pressed");
            notification.open();
        });

        login.addLoginListener(e -> {
            if ("username".equals(e.getUsername()) && "password".equals(e.getPassword())) {
                notification.setText("Successful login");
                notification.open();
                return;
            }

            login.setError(true);
            login.setEnabled(true);
        });

        add(login, notification);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {
        login.setEnabled(!"disable-login".equals(s));

        if ("custom-footer".equals(s)) {
            Div footer = new Div(VaadinIcon.VAADIN_H.create(), new Label("Custom footer"));
            login.setFooter(footer);
        } else {
            login.setFooter(null);
        }
    }
}

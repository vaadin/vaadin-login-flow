package com.vaadin.flow.component.login.examples;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

@Route
public class OverlayView extends AbstractView {

    private final LoginOverlay login = new LoginOverlay();

    public OverlayView() {
        init(login);
        login.addLoginListener(e -> login.close());
        NativeButton button = new NativeButton("open");
        button.setId("open");
        button.addClickListener(e -> login.setOpened(true));
        add(button);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent,  @OptionalParameter String s) {
        super.setParameter(beforeEvent, s);
        if ("component-title".equals(s)) {
            Div div = new Div(VaadinIcon.VAADIN_H.create(), new H3("Component title"));
            div.setId("componentTitle");
            login.setTitle(div);
        }
        if ("property-title-description".equals(s)) {
            login.setTitle("Property title");
            login.setDescription("Property description");
        }
    }
}

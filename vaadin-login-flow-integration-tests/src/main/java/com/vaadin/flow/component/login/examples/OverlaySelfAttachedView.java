package com.vaadin.flow.component.login.examples;

import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.Route;

@Route
public class OverlaySelfAttachedView extends AbstractView {

    private final LoginOverlay login = new LoginOverlay();

    public OverlaySelfAttachedView() {
        login.addLoginListener(e -> login.close());
        NativeButton button = new NativeButton("open");
        button.setId("open");
        button.addClickListener(e -> {
            ((LoginOverlay) login).setOpened(true);
        });
        add(button);
    }
}

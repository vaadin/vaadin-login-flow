package com.vaadin.flow.component.login;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.shared.Registration;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class LoginTest {

    @Test
    public void onForgotPasswordEvent() {
        Login systemUnderTest = new Login();

        AtomicInteger count = new AtomicInteger(0);
        systemUnderTest.addForgotPasswordListener(e -> count.incrementAndGet());

        ComponentUtil.fireEvent(systemUnderTest, new Login.ForgotPasswordEvent(systemUnderTest, false));

        Assert.assertEquals(1, count.get());
    }

    @Test
    public void onLoginEvent() {
        Login systemUnderTest = new Login();

        AtomicInteger count = new AtomicInteger(0);
        systemUnderTest.addLoginListener(e -> {
            Assert.assertEquals("username", e.getUsername());
            Assert.assertEquals("password", e.getPassword());
            count.incrementAndGet();
        });

        ComponentEventListener<Login.LoginEvent> listener = e -> count.incrementAndGet();

        // Should be possible to add 2 the same listeners
        systemUnderTest.addLoginListener(listener);
        Registration reg = systemUnderTest.addLoginListener(listener);

        ComponentUtil.fireEvent(systemUnderTest, new Login.LoginEvent(systemUnderTest, false,
                "username", "password"));

        Assert.assertEquals(3, count.get());

        // Should be possible to remove listener
        reg.remove();

        ComponentUtil.fireEvent(systemUnderTest, new Login.LoginEvent(systemUnderTest, false,
                "username", "password"));

        Assert.assertEquals(5, count.get());
    }
}

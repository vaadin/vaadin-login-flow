package com.vaadin.flow.component.login.test;

import com.vaadin.flow.component.login.testbench.AbstractLoginElement;
import com.vaadin.flow.component.login.testbench.LoginElement;
import com.vaadin.flow.component.login.testbench.LoginOverlayElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OverlayIT extends BasicIT {

    @Before
    public void init() {
        getDriver().get(getBaseURL() + "/overlay");
    }

    @Override
    public AbstractLoginElement getLogin() {
        $("button").id("open").click();
        return $(LoginOverlayElement.class).waitForFirst().getLogin();
    }

    @Test
    public void testOverlaySelfAttached() {
        getDriver().get(getBaseURL() + "/overlayselfattached");

        Assert.assertFalse($(LoginOverlayElement.class).exists());
        $("button").id("open").click();

        LoginOverlayElement loginOverlay = $(LoginOverlayElement.class).waitForFirst();
        Assert.assertTrue(loginOverlay.isOpened());

        AbstractLoginElement login = loginOverlay.getLogin();

        login.getUsernameField().setValue("value");
        login.getPasswordField().setValue("value");
        login.submit();

        Assert.assertFalse($(LoginOverlayElement.class).exists());
    }

}

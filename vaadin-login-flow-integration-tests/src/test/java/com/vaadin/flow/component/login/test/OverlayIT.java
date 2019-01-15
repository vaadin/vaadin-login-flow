package com.vaadin.flow.component.login.test;

import com.vaadin.flow.component.login.testbench.LoginElement;
import com.vaadin.flow.component.login.testbench.LoginOverlayElement;
import com.vaadin.testbench.TestBenchElement;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class OverlayIT extends BasicIT {

    @Override
    protected String getBaseURL() {
        return super.getBaseURL() + "/overlay";
    }

    @Override
    public LoginElement getLogin() {
        openOverlay();
        return $(LoginOverlayElement.class).waitForFirst().getLogin();
    }

    private void openOverlay() {
        $("button").waitForFirst().click();
    }

    @Override
    public void testDefaultStrings() {
        super.testDefaultStrings();
        LoginOverlayElement loginOverlay = $(LoginOverlayElement.class).waitForFirst();
        Assert.assertEquals("App name", loginOverlay.getTitle());
        Assert.assertEquals("Application description", loginOverlay.getDescription());
    }

    @Test
    public void testOverlaySelfAttached() {
        getDriver().get(super.getBaseURL() + "/overlayselfattached");

        Assert.assertFalse($(LoginOverlayElement.class).exists());
        openOverlay();

        LoginOverlayElement loginOverlay = $(LoginOverlayElement.class).waitForFirst();
        Assert.assertTrue(loginOverlay.isOpened());

        loginOverlay.getUsernameField().setValue("value");
        loginOverlay.getPasswordField().setValue("value");
        loginOverlay.submit();

        Assert.assertFalse($(LoginOverlayElement.class).exists());
    }

    @Test
    public void testTitleComponent() {
        getDriver().get(getBaseURL() + "/component-title");
        openOverlay();

        LoginOverlayElement loginOverlay = $(LoginOverlayElement.class).waitForFirst();
        TestBenchElement title = loginOverlay.getTitleComponent();

        Assert.assertEquals("Component title", title.getText());

        checkSuccessfulLogin(loginOverlay.getLogin(), () -> loginOverlay.submit());

        Assert.assertFalse(loginOverlay.isOpened());
        openOverlay();
        Assert.assertTrue(loginOverlay.isOpened());

        title = loginOverlay.getTitleComponent();
        Assert.assertEquals("vaadin:vaadin-h",
                title.$("iron-icon").first().getAttribute("icon"));

        Assert.assertEquals("Component title",
                title.$("h3").first().getText());
    }

    @Test
    public void testTitleAndDescriptionStrings() {
        getDriver().get(getBaseURL() + "/property-title-description");
        openOverlay();

        LoginOverlayElement loginOverlay = $(LoginOverlayElement.class).waitForFirst();
        Assert.assertEquals("Property title", loginOverlay.getTitle());
        Assert.assertEquals("Property description", loginOverlay.getDescription());
    }

    @Test
    @Ignore("Overlay doesn't support custom footer at this moment")
    // https://github.com/vaadin/vaadin-login/issues/67
    @Override
    public void footer() {
        super.footer();
    }
}

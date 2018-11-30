package com.vaadin.flow.component.login.test;

import com.vaadin.flow.component.login.testbench.AbstractLoginElement;
import com.vaadin.flow.component.login.testbench.LoginElement;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.testbench.parallel.BrowserUtil;
import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;

public class LoginIT extends BasicIT {

    @Override
    public void init() {
        getDriver().get(getBaseURL());
    }

    @Override
    public AbstractLoginElement getLogin() {
        return $(LoginElement.class).waitForFirst();
    }

    @Test
    public void forgotPassword() {
        AbstractLoginElement login = getLogin();
        login.forgotPassword();
        String notification = $(NotificationElement.class).waitForFirst().getText();
        Assert.assertEquals("Forgot password button pressed",
                notification);
    }

    @Test
    public void disabledLogin() {
        getDriver().get(getBaseURL() + "/disabledlogin");
        AbstractLoginElement login = getLogin();
        login.getUsernameField().setValue("username");
        login.getPasswordField().setValue("password");
        login.submit();

        Assert.assertTrue("Form submit redirect happened, but it should not",
                getDriver().getCurrentUrl().endsWith("disabledlogin"));

        if (BrowserUtil.isEdge(getDesiredCapabilities())) {
            throw new AssumptionViolatedException("Skip for Edge due to the sendKeys usage");
        }
        login.sendKeys(Keys.ENTER);
        Assert.assertTrue("Form submit redirect happened, but it should not",
                getDriver().getCurrentUrl().endsWith("disabledlogin"));
    }

    @Test
    public void failedLogin() {
        AbstractLoginElement login = getLogin();

        login.getUsernameField().setValue("username");
        login.getPasswordField().setValue("wrongPassword");
        login.submit();
        String notification = $(NotificationElement.class).waitForFirst().getText();
        Assert.assertEquals("Login failed", notification);
    }

    @Test
    public void actionLogin() {
        getDriver().get(getBaseURL() + "/action");
        AbstractLoginElement login = getLogin();

        login.getUsernameField().setValue("username");
        login.getPasswordField().setValue("wrongPassword");
        login.submit();
        Assert.assertTrue("Redirect didn't happened on login",
                getDriver().getCurrentUrl().endsWith("process-login-here"));
    }
}

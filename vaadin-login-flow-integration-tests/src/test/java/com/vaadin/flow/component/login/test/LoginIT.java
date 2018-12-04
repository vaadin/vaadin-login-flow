package com.vaadin.flow.component.login.test;

import com.vaadin.flow.component.login.testbench.LoginElement;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.testbench.parallel.BrowserUtil;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;

public class LoginIT extends BasicIT {

    @Override
    public void init() {
        getDriver().get(getBaseURL());
    }

    @Override
    public LoginElement getLogin() {
        return $(LoginElement.class).waitForFirst();
    }

    @Test
    public void forgotPassword() {
        checkForgotPassword(getLogin());
    }

    private void checkForgotPassword(LoginElement login) {
        login.forgotPassword();
        String notification = $(NotificationElement.class).waitForFirst().getText();
        Assert.assertEquals("Forgot password button pressed",
                notification);
    }

    @Test
    public void disabledLogin() {
        getDriver().get(getBaseURL() + "/disable-login");
        LoginElement login = getLogin();
        login.getUsernameField().setValue("username");
        login.getPasswordField().setValue("password");
        login.submit();

        Assert.assertFalse("Login notification was shown",
                $(NotificationElement.class).waitForFirst().isOpen());

        if (BrowserUtil.isEdge(getDesiredCapabilities())) {
            skipTest("Skip for Edge due to the sendKeys usage");
        }
        login.getPasswordField().focus();
        login.sendKeys(Keys.ENTER);
        Assert.assertFalse("Login notification was shown",
                $(NotificationElement.class).waitForFirst().isOpen());
        if (BrowserUtil.isIE(getDesiredCapabilities())) {
            skipTest("Temporary Skip IE until disabled property won't reflectToAttribute");
            Assert.assertFalse("Disabled property should not reflect to attribute", login.hasAttribute("disabled"));
        }
        // Forgot password event should be processed anyway
        checkForgotPassword(login);
    }

    @Test
    public void enterKeyLogin() {
        if (BrowserUtil.isEdge(getDesiredCapabilities())) {
            skipTest("Skip for Edge due to the sendKeys usage");
        }
        LoginElement login = getLogin();
        checkSuccessfulLogin(login, () -> {
            login.focus();
            login.sendKeys(Keys.ENTER);
        });
    }
    @Test
    public void passwordEnterKeyLogin() {
        if (BrowserUtil.isEdge(getDesiredCapabilities())) {
            skipTest("Skip for Edge due to the sendKeys usage");
        }
        LoginElement login = getLogin();
        checkSuccessfulLogin(login, () -> {
            login.getPasswordField().focus();
            login.sendKeys(Keys.ENTER);
        });
    }

    @Test
    public void usernameEnterKeyLogin() {
        if (BrowserUtil.isEdge(getDesiredCapabilities())) {
            skipTest("Skip for Edge due to the sendKeys usage");
        }
        LoginElement login = $(LoginElement.class).waitForFirst();
        checkSuccessfulLogin(login, () -> {
            login.getUsernameField().focus();
            login.sendKeys(Keys.ENTER);
        });
    }

    @Test
    public void failedLogin() {
        LoginElement login = getLogin();

        login.getUsernameField().setValue("username");
        login.getPasswordField().setValue("wrongPassword");
        login.submit();
        String notification = $(NotificationElement.class).waitForFirst().getText();
        Assert.assertEquals("Login failed", notification);
    }

    @Test
    public void actionLogin() {
        getDriver().get(getBaseURL() + "/action");
        LoginElement login = getLogin();

        login.getUsernameField().setValue("username");
        login.getPasswordField().setValue("wrongPassword");
        login.submit();
        Assert.assertTrue("Redirect didn't happened on login",
                getDriver().getCurrentUrl().endsWith("process-login-here"));
    }
}

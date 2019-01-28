package com.vaadin.flow.component.login.test;

import com.vaadin.flow.component.login.testbench.LoginFormElement;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.parallel.BrowserUtil;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;

public class LoginFormIT extends BasicIT {

    @Override
    public void init() {
        getDriver().get(getBaseURL());
    }

    @Override
    public LoginFormElement getLoginForm() {
        return $(LoginFormElement.class).waitForFirst();
    }

    @Test
    public void login() {
        LoginFormElement login = getLoginForm();
        checkSuccessfulLogin(login.getUsernameField(), login.getPasswordField(), () -> login.submit());
    }

    @Override
    public void testDefaults() {
        super.testDefaults();
        LoginFormElement login = getLoginForm();
        checkLoginForm(login.getUsernameField(), login.getPasswordField(), login.getSubmitButton());
    }

    @Test
    public void forgotPassword() {
        checkForgotPassword(getLoginForm());
    }

    private void checkForgotPassword(LoginFormElement login) {
        login.forgotPassword();
        String notification = $(NotificationElement.class).waitForFirst().getText();
        Assert.assertEquals("Forgot password button pressed",
                notification);
    }

    @Test
    public void disabledLogin() {
        getDriver().get(getBaseURL() + "/disable-login");
        LoginFormElement login = getLoginForm();
        login.getUsernameField().setValue("username");
        login.getPasswordField().setValue("password");
        login.submit();

        Assert.assertFalse("Login notification was shown",
                $(NotificationElement.class).waitForFirst().isOpen());

        if (BrowserUtil.isEdge(getDesiredCapabilities())) {
            skipTest("Skip for Edge due to the sendKeys usage");
        }
        login.getPasswordField().sendKeys(Keys.ENTER);
        Assert.assertFalse("Login notification was shown",
                $(NotificationElement.class).waitForFirst().isOpen());

        Assert.assertFalse("Disabled property should not reflect to attribute", login.hasAttribute("disabled"));
        // Forgot password event should be processed anyway
        checkForgotPassword(login);
    }

    @Test
    public void passwordEnterKeyLogin() {
        if (BrowserUtil.isEdge(getDesiredCapabilities())) {
            skipTest("Skip for Edge due to the sendKeys usage");
        }
        LoginFormElement login = getLoginForm();
        checkSuccessfulLogin(login.getUsernameField(), login.getPasswordField(), () -> {
            login.getPasswordField().sendKeys(Keys.ENTER);
        });
    }

    @Test
    public void usernameEnterKeyLogin() {
        if (BrowserUtil.isEdge(getDesiredCapabilities())) {
            skipTest("Skip for Edge due to the sendKeys usage");
        }
        LoginFormElement login = getLoginForm();
        checkSuccessfulLogin(login.getUsernameField(), login.getPasswordField(), () -> {
            login.getUsernameField().sendKeys(Keys.ENTER);
        });
    }

    @Test
    public void failedLogin() {
        LoginFormElement login = getLoginForm();

        TestBenchElement errorMessage = login.getErrorComponent();
        // TODO #isDisplayed() should be used when safari 12 is in use
        Assert.assertTrue(errorMessage.hasAttribute("hidden"));

        login.getUsernameField().setValue("username");
        login.getPasswordField().setValue("wrongPassword");
        login.submit();

        // TODO #isDisplayed() should be used when safari 12 is in use
        Assert.assertFalse(errorMessage.hasAttribute("hidden"));
        Assert.assertEquals("Incorrect username or password", login.getErrorMessageTitle());
        Assert.assertEquals("Check that you have entered the correct username and password and try again.",
                login.getErrorMessage());

        Assert.assertTrue(login.isEnabled());
        login.submit();
        Assert.assertTrue(login.isEnabled());
        login.submit();

        // Should be disabled after 3 attempts
        // TODO Uncomment when client part wil be able to set Error without autoEnable
        // Assert.assertFalse(login.isEnabled());
    }

    @Test
    public void actionLogin() {
        getDriver().get(getBaseURL() + "/action");
        LoginFormElement login = getLoginForm();

        login.getUsernameField().setValue("username");
        login.getPasswordField().setValue("password");
        login.submit();
        Assert.assertTrue("Redirect didn't happened on login",
                getDriver().getCurrentUrl().endsWith("process-login-here"));
    }
}

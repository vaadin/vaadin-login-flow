package com.vaadin.flow.component.login.test;

import com.vaadin.flow.component.login.testbench.LoginElement;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.flow.component.textfield.testbench.PasswordFieldElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.parallel.BrowserUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class BasicIT extends AbstractParallelTest {

    @Before
    public void init() {
        getDriver().get(getBaseURL());
    }

    public abstract LoginElement getLogin();

    @Test
    public void testDefaultStrings() {
        LoginElement login = getLogin();

        Assert.assertEquals("Log in", login.getFormTitle());
        if (!BrowserUtil.isEdge(getDesiredCapabilities()) && !BrowserUtil.isSafari(getDesiredCapabilities())) {
            // Error message should be hidden by default, however Safari and Edge drivers return the innerHTML content
            Assert.assertEquals("", login.getErrorMessageTitle());
            Assert.assertEquals("", login.getErrorMessage());
        }
        Assert.assertEquals("Username", login.getUsernameField().getLabel());
        Assert.assertEquals("Password", login.getPasswordField().getLabel());
        Assert.assertEquals("Log in", login.getSubmitButton().getText());
        Assert.assertEquals("Forgot password", login.getForgotPasswordButton().getText());
        Assert.assertEquals("In case you need to provide some additional info for the user.",
                login.getAdditionalInformation());
    }

    @Test
    public void login() {
        LoginElement login = getLogin();
        checkSuccessfulLogin(login.getUsernameField(), login.getPasswordField(), () -> login.submit());
    }

    protected void checkSuccessfulLogin(TextFieldElement usernameField, PasswordFieldElement passwordField,
                                        Runnable submit) {
        usernameField.setValue("username");
        passwordField.setValue("password");
        submit.run();
        String notification = $(NotificationElement.class).waitForFirst().getText();
        Assert.assertEquals("Successful login", notification);
    }

    @Test
    public void footer() {
        getDriver().get(getBaseURL() + "/custom-footer");

        LoginElement login = getLogin();
        TestBenchElement footer = login.getFooter();

        Assert.assertEquals("Custom footer", footer.getText());
        Assert.assertTrue(footer.$("iron-icon").exists());
    }

}

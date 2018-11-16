package com.vaadin.flow.component.login.test;

import com.vaadin.flow.component.login.testbench.LoginElement;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.testbench.parallel.BrowserUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BasicIT extends AbstractParallelTest {

    @Before
    public void init() {
        getDriver().get(getBaseURL());
    }

    @Test
    public void testDefaultStrings() {
        LoginElement login = $(LoginElement.class).waitForFirst();

        Assert.assertEquals("App name", login.getTitle());
        Assert.assertEquals("Inspiring application description", login.getMessage());
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
    public void testI18n() {
        LoginElement login = $(LoginElement.class).waitForFirst();
    //TODO
        Assert.assertEquals("Nome do aplicativo", login.getTitle());
        Assert.assertEquals("Descrição do aplicativo", login.getMessage());
        Assert.assertEquals("Acesse a sua conta", login.getFormTitle());
        if (!BrowserUtil.isEdge(getDesiredCapabilities()) && !BrowserUtil
            .isSafari(getDesiredCapabilities())) {
            // Error message should be hidden by default, however Safari and Edge drivers return the innerHTML content
            // Usuário/senha inválidos
            Assert.assertEquals("", login.getErrorMessageTitle());
            //Confira seu usuário e senha e tente novamente.
            Assert.assertEquals("", login.getErrorMessage());
        }
        Assert.assertEquals("Usuário", login.getUsernameField().getLabel());
        Assert.assertEquals("Senha", login.getPasswordField().getLabel());
        Assert.assertEquals("Entrar", login.getSubmitButton().getText());
        Assert.assertEquals("Esqueci minha senha",
            login.getForgotPasswordButton().getText());
        Assert.assertEquals(
            "Caso necessite apresentar alguma informação extra para o usuário (como credenciais padrão), este é o lugar.",
            login.getAdditionalInformation());
    }

    @Test
    public void forgotPassword() {
        LoginElement login = $(LoginElement.class).waitForFirst();
        login.forgotPassword();
        String notification = $(NotificationElement.class).waitForFirst().getText();
        Assert.assertEquals("Forgot password button pressed",
                notification);
    }
}

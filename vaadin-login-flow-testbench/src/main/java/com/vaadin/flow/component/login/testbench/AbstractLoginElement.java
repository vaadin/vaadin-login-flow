package com.vaadin.flow.component.login.testbench;

/*
 * #%L
 * Vaadin Login Testbench API
 * %%
 * Copyright (C) 2018 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 *
 * See the file license.html distributed with this software for more
 * information about licensing.
 *
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <http://vaadin.com/license/cval-3>.
 * #L%
 */

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.textfield.testbench.PasswordFieldElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.TestBenchElement;

/**
 * Abstract element for <code>&lt;vaadin-login&gt;</code> and
 * <code>&lt;vaadin-login-overlay&gt;</code> components
 */
public abstract class AbstractLoginElement extends TestBenchElement {

    public abstract AbstractLoginElement getLogin();

    /**
     * Returns the username field
     */
    public TextFieldElement getUsernameField() {
        return getLogin().$(TextFieldElement.class).id("username");
    }

    /**
     * Returns the password field
     */
    public PasswordFieldElement getPasswordField() {
        return getLogin().$(PasswordFieldElement.class).id("password");
    }

    /**
     * Returns the log in button
     */
    public ButtonElement getSubmitButton() {
        return getLogin().$(ButtonElement.class).id("submit");
    }

    /**
     * Provides a shortcut for clicking the submit button
     */
    public void submit() {
        getSubmitButton().click();
    }

    /**
     * Returns the forgot password button
     */
    public ButtonElement getForgotPasswordButton() {
        return getLogin().$(ButtonElement.class).id("forgotPasswordButton");
    }

    /**
     * Provides a shortcut for clicking the forgot password button
     */
    public void forgotPassword() {
        getForgotPasswordButton().click();
    }

    /**
     * Returns the title of the login element
     */
    public String getTitle() {
        return getLogin().$(TestBenchElement.class)
                .attribute("part", "brand").first().$("h1").first().getText();
    }

    /**
     * Returns the message under the title of the login element
     */
    public String getMessage() {
        return getLogin().$(TestBenchElement.class)
                .attribute("part", "brand").first().$("p").first().getText();
    }

    /**
     * Returns the form title of the login element
     */
    public String getFormTitle() {
        return getLogin().$(TestBenchElement.class)
                .attribute("part", "form").first().$("h2").first().getText();
    }

    /**
     * Returns the error message title. Returns empty string
     * if the error message is not displayed
     */
    public String getErrorMessageTitle() {
        return getLogin().$(TestBenchElement.class)
                .attribute("part", "error-message").first().$("h5").first().getText();
    }

    /**
     * Returns the error message text. Returns empty string
     * if the error message is not displayed
     */
    public String getErrorMessage() {
        return getLogin().$(TestBenchElement.class)
                .attribute("part", "error-message").first().$("p").first().getText();
    }

    /**
     * Returns the additional information placed in a footer
     * of the login element
     */
    public String getAdditionalInformation() {
        return getLogin().$(TestBenchElement.class)
                .attribute("part", "footer").first().$("p").first().getText();
    }
}

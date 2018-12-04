package com.vaadin.flow.component.login.testbench;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.textfield.testbench.PasswordFieldElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;

public interface ILoginElement {

    /**
     * Return the username field
     */
    TextFieldElement getUsernameField();

    /**
     * Return the password field
     */
    PasswordFieldElement getPasswordField();

    /**
     * Return the log in button
     */
    ButtonElement getSubmitButton();

    /**
     * Provide a shortcut for clicking the submit button
     */
    default void submit() {
        getSubmitButton().click();
    }

    /**
     * Return the forgot password button
     */
    ButtonElement getForgotPasswordButton();

    /**
     * Provide a shortcut for clicking the forgot password button
     */
    default void forgotPassword() {
        getForgotPasswordButton().click();
    }

    /**
     * Return the title of the login element
     */
    String getTitle();

    /**
     * Return the message under the title of the login element
     */
    String getMessage();

    /**
     * Return the form title of the login element
     */
    String getFormTitle();

    /**
     * Return the error message title. Returns empty string
     * if the error message is not displayed
     */
    String getErrorMessageTitle();

    /**
     * Return the error message text. Returns empty string
     * if the error message is not displayed
     */
    String getErrorMessage();

    /**
     * Return the additional information placed in a footer
     * of the login element
     */
    String getAdditionalInformation();
}

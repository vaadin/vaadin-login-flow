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
import com.vaadin.testbench.elementsbase.Element;

@Element("vaadin-login")
public class LoginElement extends TestBenchElement {

    // TODO: get by id when new webjar is in use
    public TextFieldElement getUsernameField() {
        return $(TextFieldElement.class).first();
    }

    public PasswordFieldElement getPasswordField() {
        return $(PasswordFieldElement.class).first();
    }

    public ButtonElement getSubmitButton() {
        return $(ButtonElement.class).id("submit");
    }

    public void submit() {
        getSubmitButton().click();
    }

    public ButtonElement getForgotPasswordButton() {
        return $(ButtonElement.class).id("forgotPasswordButton");
    }

    public void forgotPassword() {
        getForgotPasswordButton().click();
    }

    public String getTitle() {
        return $(TestBenchElement.class)
                .attribute("part", "brand").first().$("h1").first().getText();
    }

    public String getMessage() {
        return $(TestBenchElement.class)
                .attribute("part", "brand").first().$("p").first().getText();
    }

    public String getFormTitle() {
        return $(TestBenchElement.class)
                .attribute("part", "form").first().$("h2").first().getText();
    }

    public String getErrorMessageTitle() {
        return $(TestBenchElement.class)
                .attribute("part", "error-message").first().$("h5").first().getText();
    }

    public String getErrorMessage() {
        return $(TestBenchElement.class)
                .attribute("part", "error-message").first().$("p").first().getText();
    }

    public String getAdditionalInformation() {
        return $(TestBenchElement.class)
                .attribute("part", "footer").first().$("p").first().getText();
    }
}

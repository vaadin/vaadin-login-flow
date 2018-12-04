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

/**
 * TestBench element for the <code>&lt;vaadin-login&gt;</code> element
 */
@Element("vaadin-login")
public class LoginElement extends TestBenchElement implements ILoginElement {

    @Override
    public TextFieldElement getUsernameField() {
        return $(TextFieldElement.class).id("username");
    }

    @Override
    public PasswordFieldElement getPasswordField() {
        return $(PasswordFieldElement.class).id("password");
    }

    @Override
    public ButtonElement getSubmitButton() {
        return $(ButtonElement.class).id("submit");
    }

    @Override
    public ButtonElement getForgotPasswordButton() {
        return $(ButtonElement.class).id("forgotPasswordButton");
    }

    @Override
    public String getTitle() {
        return $(TestBenchElement.class)
                .attribute("part", "brand").first().$("h1").first().getText();
    }

    @Override
    public String getMessage() {
        return $(TestBenchElement.class)
                .attribute("part", "brand").first().$("p").first().getText();
    }

    @Override
    public String getFormTitle() {
        return $(TestBenchElement.class)
                .attribute("part", "form").first().$("h2").first().getText();
    }

    @Override
    public String getErrorMessageTitle() {
        return $(TestBenchElement.class)
                .attribute("part", "error-message").first().$("h5").first().getText();
    }

    @Override
    public String getErrorMessage() {
        return $(TestBenchElement.class)
                .attribute("part", "error-message").first().$("p").first().getText();
    }

    @Override
    public String getAdditionalInformation() {
        return $(TestBenchElement.class)
                .attribute("part", "footer").first().$("p").first().getText();
    }
}

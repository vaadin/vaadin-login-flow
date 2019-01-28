package com.vaadin.flow.component.login.testbench;

/*
 * #%L
 * Vaadin Login Testbench API
 * %%
 * Copyright (C) 2018 Vaadin Ltd
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.textfield.testbench.PasswordFieldElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

/**
 * TestBench element for the <code>&lt;vaadin-login-form-wrapper&gt;</code> element
 */
@Element("vaadin-login-form-wrapper")
public class LoginFormWrapperElement extends TestBenchElement {

    public ButtonElement getForgotPasswordButton() {
        return $(ButtonElement.class).id("forgotPasswordButton");
    }

    public String getFormTitle() {
        return $(TestBenchElement.class)
                .attribute("part", "form").first().$("h2").first().getText();
    }

    public TestBenchElement getErrorComponent() {
        return $(TestBenchElement.class).attribute("part", "error-message").first();
    }

    public String getErrorMessageTitle() {
        return getErrorComponent().$("h5").first().getText();
    }

    public String getErrorMessage() {
        return getErrorComponent().$("p").first().getText();
    }

    public String getAdditionalInformation() {
        return $(TestBenchElement.class)
                .attribute("part", "footer").first().$("p").first().getText();
    }
}

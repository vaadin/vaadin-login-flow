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

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * TestBench element for the <code>&lt;vaadin-login-overlay&gt;</code> element
 */
@Element("vaadin-login-overlay")
public class LoginOverlayElement extends AbstractLoginElement {

    public TestBenchElement getLoginOverlayElement() {
        return $("vaadin-login-overlay-element").onPage().waitForFirst();
    }

    @Override
    public AbstractLoginElement getLogin() {
        return getLoginOverlayElement().$(LoginElement.class).first();
    }

    public boolean isOpened() {
        try {
            return this.getPropertyBoolean(new String[]{"opened"});
        } catch (StaleElementReferenceException var2) {
            return false;
        }
    }
}

package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import org.testng.annotations.Test;

public class SelzyAuthorizationTest {

    private static final String BASE_URL = "https://cp.selzy.com/auth/signin";

    @Test
    public void testSuccessfulLogin() {
        Selenide.open(BASE_URL);
        Selenide.$("#email")
                .setValue("testuser1@selzy.com");
        Selenide.$("#password")
                .setValue("testpassword1");
        Selenide.$(".ant-btn")
                .click();
        Selenide.$(".ant-menu-submenu-title.ng-star-inserted")
                .shouldHave(Condition.text("Dashboard"));
    }

    @Test
    public void testInvalidLogin() {
        Selenide.open(BASE_URL);
        Selenide.$("#email")
                .setValue("invalidemail@selzy.com");
        Selenide.$("#password")
                .setValue("testpassword1");
        Selenide.$(".ant-btn")
                .click();
        Selenide.$(".ant-form-item-explain.ant-form-item-explain-error")
                .shouldHave(Condition.text("This email is not registered with Selzy."));
    }

    @Test
    public void testInvalidPassword() {
        Selenide.open(BASE_URL);
        Selenide.$("#email")
                .setValue("testuser1@selzy.com");
        Selenide.$("#password")
                .setValue("invalidpassword");
        Selenide.$(".ant-btn")
                .click();
        Selenide.$(".ant-form-item-explain.ant-form-item-explain-error")
                .shouldHave(Condition.text("Wrong password. Try again or click on \"Restore\""));
    }

    @Test
    public void testEmptyEmail() {
        Selenide.open(BASE_URL);
        Selenide.$("#password")
                .setValue("testpassword1");
        Selenide.$(".ant-btn")
                .click();
        Selenide.$(".ant-form-item-explain.ant-form-item-explain-error")
                .shouldHave(Condition.text("Enter email"));
    }

    @Test
    public void testEmptyPassword() {
        Selenide.open(BASE_URL);
        Selenide.$("#email")
                .setValue("testuser1@selzy.com");
        Selenide.$(".ant-btn")
                .click();
        Selenide.$(".ant-form-item-explain.ant-form-item-explain-error")
                .shouldHave(Condition.text("Enter password"));
    }

    @Test(enabled = false)
    public void testForgotPasswordLink() {
    }

}

package tests;

import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.example.core.BaseTest;
import com.example.data.User;
import com.example.pages.AuthPage;
import com.example.pages.HomePage;
import com.example.pages.MyAccountPage;
import com.example.actions.AuthFlow;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    @Attributes(attributes = {
            @Attribute(key = "layer", value = "ui"),
            @Attribute(key = "type", value = "regression"),
            @Attribute(key = "feature", value = "login")
    })
    @Test(description = "User sees an error message when login fails with wrong credentials")
    public void loginErrorTest() {
        AuthPage auth = new HomePage(driver).clickSignIn();
        assertThat(driver.getCurrentUrl())
                .as("Should be on authentication page")
                .contains("controller=authentication");

        auth.typeEmail("wrong@example.com")
                .typePassword("badpass")
                .clickSubmitButton();

        assertThat(auth.isLoginFormVisible()).isTrue();
        assertThat(auth.getLoginErrorMessage())
                .as("Invalid login should show an error")
                .containsIgnoringCase("authentication failed");
    }

    @Attributes(attributes = {
            @Attribute(key = "layer", value = "ui"),
            @Attribute(key = "type", value = "smoke"),
            @Attribute(key = "feature", value = "login")
    })
    @Test(description = "User should successfully log in and land on My Account page")
    public void loginSuccessTest() {
        User user = new User(CFG.userEmail(), CFG.userPassword());

        MyAccountPage account = new AuthFlow(driver).login(user);

        assertThat(account.getHeaderText())
                .as("After successful login, 'My account' header should be visible")
                .containsIgnoringCase("my account");
        assertThat(account.getCurrentUrl())
                .as("URL should contain 'controller=my-account'")
                .contains("controller=my-account");

        AuthPage backToAuth = account.clickSignOut();
        assertThat(backToAuth.getCurrentUrl())
                .as("After sign out, should be back on authentication page")
                .contains("controller=authentication");
        assertThat(backToAuth.isLoginFormVisible()).isTrue();
    }
}


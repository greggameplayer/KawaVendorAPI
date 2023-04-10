package com.kawa.cucumber.stepdefs;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.context.annotation.Scope;

@Scope(SCOPE_CUCUMBER_GLUE)
public class ConnectionStepDefs extends StepDefs {

    @Given("His admin rights")
    public void hisAdminRights() {
        createAdminUser();
    }

    @Given("His normal rights")
    public void hisNormalRights() {
        createUser();
    }

    @When("He tries to get the vendors list")
    public void heTriesToGetTheVendorsList() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        context.currentResultAction = context.requestGet("/api/vendors", user);
    }

    @Then("He should have a success response")
    public void heShouldHaveASuccessResponse() {
        assertThat(context.currentResultAction.andReturn().getResponse().getStatus()).isEqualTo(200);
    }

    @Then("He should have a forbidden response")
    public void heShouldHaveAForbiddenResponse() {
        assertThat(context.currentResultAction.andReturn().getResponse().getStatus()).isEqualTo(403);
    }
}

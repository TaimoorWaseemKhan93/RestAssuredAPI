package stepdefs;

import Actions.CasesActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CasesSteps extends CasesActions {
    @Given("User set API Key in Header")
    public void userSetAPIKeyInHeader() {
        setAPIKey();
    }

    @When("User hit API for Currency")
    public void userHitAPIForCurrency() {
        currencyAPI();
    }

    @Then("User validate status code (\\d+)$")
    public void userValidateStatusCode(int statusCode) {
        getIDs();
        verifyStatusCode(statusCode);
    }

    @And("User validate response")
    public void userValidateResponse() {
    }

    @When("User convert Currencies")
    public void userConvertCurrencies() {
        convertCurrencies();
    }

    @Then("User verify conversion")
    public void userVerifyConversion() {
    }

    @Then("User again validate status code {int}")
    public void userAgainValidateStatusCode(int statucCode) {
        verifyStatusCode(statucCode);
    }

    @When("User hit info Currency API")
    public void userHitInfoCurrencyAPI() {
        infoAPICall();
    }

    @Then("Verify response has {int} countries")
    public void verifyResponseHasCountries(int arg0) {

    }

    @And("Print all {int} Countries")
    public void printAllCountries(int arg0) {
    }

    @When("User hit for {int} Countries info Currency API")
    public void userHitForCountriesInfoCurrencyAPI(int count) {
        VerifyCryptoCurrency(count);
    }
}

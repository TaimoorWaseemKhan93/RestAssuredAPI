package stepdefs;

import Configuration.Endpoints;
import Configuration.EnvGlobals;
import Configuration.configProperties;
import General.ReusableMethods;
import Validations.BackendValidations;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class BEstep {

    @Given("user enters API key")
    public void user_enters_api_key() {
        RestAssured.baseURI= configProperties.BaseURL;
        ReusableMethods.givenHeaders(ReusableMethods.headers("X-CMC_PRO_API_KEY", configProperties.APIKey));
    }

    @And("^user hits currency mapping API$")
    public void user_hits_currency_mapping_API(){
        ReusableMethods.whenFunctionWithListOfParams(configProperties.BaseURL + Endpoints.cryptoMap,"symbol","USDT,BTC,ETH");
    }

    @And("^user validates status code is (\\d+)$")
    public void user_validates_status_code(int statusCode){
        ReusableMethods.thenFunction(statusCode);
    }

    @And("^user extracts the currency Ids$")
    public void user_extracts_the_currency_Ids(){
        EnvGlobals.bitcoinId=ReusableMethods.fetchParticularIds("Bitcoin");
        System.out.println("Bitcoin id is: " + EnvGlobals.bitcoinId);
        EnvGlobals.USDTid=ReusableMethods.fetchParticularIds("Tether");
        System.out.println("USDT id is: " + EnvGlobals.USDTid);
        EnvGlobals.ethereumId=ReusableMethods.fetchParticularIds("Ethereum");
        System.out.println("Ethereum id is: " + EnvGlobals.ethereumId);
    }

    @Then("^user converts currency \"([^\"]*)\" into BOB$")
    public void user_converts_currency(String sourceCurrency){
        ReusableMethods.currencyConversion(configProperties.BaseURL + Endpoints.conversionTool,configProperties.conversionAmount,sourceCurrency);
    }

    @Then("^user hits currency info API$")
    public void user_hits_currency_info_API() {
        ReusableMethods.whenFunctionWithListOfParams(configProperties.BaseURL + Endpoints.cryptoInfo , "id",EnvGlobals.ethereumId);
    }

    @And("^user validates the API response$")
    public void user_validates_the_response() {
        BackendValidations.validateCryptoInfo(1027);
    }

    @When("^user hits the currency info API$")
    public void user_hits_the_currency_info_api(){
        ReusableMethods.whenFunctionWithListOfParams(configProperties.BaseURL + Endpoints.cryptoInfo,"id",EnvGlobals.currencyId);
    }

    @When("^user verifies the currencies with mineable tag$")
    public void user_verifies_the_currencies_with_mineable_tag(){
        ReusableMethods.fetchIds();
        BackendValidations.validateFetchIds(EnvGlobals.actualDataCount);
    }
}

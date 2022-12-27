package Actions;

import MainCall.GlobalVariable;
import MainCall.configProperties;
import Data.CasesEndData;
import General.Assertions;
import General.CommonFunctions;
import Utils.ExtentReport;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.List;

public class CasesActions {

    CasesEndData casesEndData = new CasesEndData();
    Assertions assertions = new Assertions();

    public void setAPIKey() {
        RestAssured.baseURI = configProperties.BaseURL;
        CommonFunctions.givenHeaders(CommonFunctions.headers("X-CMC_PRO_API_KEY", configProperties.APIKey));
        ExtentReport.logPass("Header set successfully");
    }

    public void currencyAPI() {
        CommonFunctions.whenFunctionWithParams(configProperties.BaseURL + CasesEndData.map, "symbol", "USDT,BTC,ETH");
        ExtentReport.logPass("Currency hit successfully");
    }

    public void getIDs() {

        List ids = CommonFunctions.fetchResponseByPath("data");
        if (ids.size() >= 3) {
            ExtentReport.logPass("Ids fetch successfully");
            ExtentReport.logPass("Total Ids found: " + ids.size());
        } else {
            ExtentReport.logFail("Ids fetch not successful");
        }

        for (int i = 0; i < ids.size(); i++) {
            if (CommonFunctions.getResponsePath("data[" + i + "].name").equals("Bitcoin")) {
                casesEndData.setBitCoinId(CommonFunctions.getResponsePath("data[" + i + "].id"));
                ExtentReport.logPass("Bitcoin Id fetch successfully");
                break;
            } else if (i == (ids.size() - 1)) {
                ExtentReport.logFail("Bitcoin Id not fetch");
            }
        }

        for (int i = 0; i < ids.size(); i++) {
            if (CommonFunctions.getResponsePath("data[" + i + "].name").equals("Tether")) {
                casesEndData.setUSDTId(CommonFunctions.getResponsePath("data[" + i + "].id"));
                ExtentReport.logPass("USDT details found successfully.");
                break;
            } else if (i == (ids.size() - 1)) {
                ExtentReport.logFail("USDT details not found.");
            }
        }

        for (int i = 0; i < ids.size(); i++) {
            if (CommonFunctions.getResponsePath("data[" + i + "].name").equals("Ethereum")) {
                casesEndData.setEthereumId(CommonFunctions.getResponsePath("data[" + i + "].id"));
                ExtentReport.logPass("Ethereum details found successfully");
                break;
            } else if (i == (ids.size() - 1)) {
                ExtentReport.logFail("Ethereum details not found");
            }
        }
    }

    public void verifyStatusCode(int statusCode) {
        CommonFunctions.thenFunction(statusCode);
        ExtentReport.logPass("Status is 200");
    }

    public void convertCurrencies() {

        CommonFunctions.currencyConversion(configProperties.BaseURL + CasesEndData.convert, configProperties.conversionAmount, "BTC", casesEndData.getBitCoinId());
        String value = GlobalVariable.response.jsonPath().get("data.quote.BOB.price").toString();
        if (value != null) {
            ExtentReport.logPass("BTC change Successfully...");
            ExtentReport.logPass("Price: " + value);
        }

        CommonFunctions.currencyConversion(configProperties.BaseURL + CasesEndData.convert, configProperties.conversionAmount, "ETH", casesEndData.getEthereumId());
        value = GlobalVariable.response.jsonPath().get("data.quote.BOB.price").toString();
        if (value != null) {
            ExtentReport.logPass("ETH change Successfully...");
            ExtentReport.logPass("Price: " + value);
        }

        CommonFunctions.currencyConversion(configProperties.BaseURL + CasesEndData.convert, configProperties.conversionAmount, "USDT", casesEndData.getUSDTId());
        value = GlobalVariable.response.jsonPath().get("data.quote.BOB.price").toString();
        if (value != null) {
            ExtentReport.logPass("USDT change Successfully....");
            ExtentReport.logPass("Price: " + value);
        }
    }

    public void infoAPICall(){
        String id = "1027";
        String date = "";
        String logo = "";
        String technicalDoc = "";
        String symbol = "";
        if(casesEndData.getEthereumId() == null){
            CommonFunctions.whenFunctionWithParams(configProperties.BaseURL + CasesEndData.info, "id","1027");
            date = GlobalVariable.response.jsonPath().get("data." + id + ".date_added").toString();
            logo = GlobalVariable.response.jsonPath().get("data." + id + ".logo").toString();
            technicalDoc = GlobalVariable.response.jsonPath().get("data." + id + ".urls.technical_doc[0]").toString();
            symbol = GlobalVariable.response.jsonPath().get("data." + id + ".symbol").toString();
        }else {
            CommonFunctions.whenFunctionWithParams(configProperties.BaseURL + CasesEndData.info, "id",casesEndData.getEthereumId());
            date = GlobalVariable.response.jsonPath().get("data." + casesEndData.getEthereumId() + ".date_added").toString();
            logo = GlobalVariable.response.jsonPath().get("data." + casesEndData.getEthereumId() + ".logo").toString();
            technicalDoc = GlobalVariable.response.jsonPath().get("data." + casesEndData.getEthereumId() + ".urls.technical_doc[0]").toString();
            symbol = GlobalVariable.response.jsonPath().get("data." + casesEndData.getEthereumId() + ".symbol").toString();

        }

        assertions.equal(logo, "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png", "Logo found in Response\n"+logo);
        assertions.equal(technicalDoc,"https://github.com/ethereum/wiki/wiki/White-Paper", "Technical Documentation URL found in Response\n"+technicalDoc);
        assertions.equal(symbol,"ETH", "Symbol is Showing in Response\n"+symbol);
        assertions.equal(date, "2015-08-07T00:00:00.000Z", "Date is available in Response\n"+date);

        JsonPath jsonPathEvaluator = GlobalVariable.response.jsonPath();

        List dataSize = jsonPathEvaluator.get("data." + id + ".tags");
        int actualCount = dataSize.size();
        if (actualCount > 0) {
            for (int i = 0; i < actualCount; i++) {
                if (CommonFunctions.getResponsePath("data." + id + ".tags[" + i + "]").equals("mineable")) {
                    Assert.assertEquals(CommonFunctions.getResponsePath("data." + id + ".tags[" + i + "]"), "mineable");
                    break;
                } else if (i == (actualCount - 1)) {
                    ExtentReport.logFail("Mineable tag not found");
                }
            }
        } else {
            ExtentReport.logFail("Mineable tag not found");
        }
    }

    public void VerifyCryptoCurrency(int count){
        for(int i = 1; i<=count; i++) {
            CommonFunctions.whenFunctionWithParams(configProperties.BaseURL + CasesEndData.info, "id", Integer.toString(i));
            List data = GlobalVariable.response.jsonPath().get("data."+i+".tags");
            for(int j=0; j< data.size();j++){
                if(GlobalVariable.response.jsonPath().get("data." + (i) + ".tags[" + j + "]").equals("mineable")){
                    ExtentReport.logPass(GlobalVariable.response.jsonPath().get("data."+i+".name") + " has mineable tag");
                }
            }
        }

    }
}

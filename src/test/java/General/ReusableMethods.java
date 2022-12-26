package General;

import Configuration.EnvGlobals;
import Configuration.configProperties;
import com.mysql.cj.exceptions.AssertionFailedException;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReusableMethods {

    public static List id;
    public static RequestSpecification REQUEST;

    public static void contentType(String contentType) {
        REQUEST = RestAssured.given().contentType(contentType);
        EnvGlobals.requestSpecification = REQUEST.given();
    }

    public static void givenHeaders(Map<String, String> headers) {
        contentType("application/json");
        EnvGlobals.requestSpecification = REQUEST.given().headers(headers);
    }

    public static void whenFunctionWithListOfParams(String endPoint,String queryParam,String currencyId) {
                EnvGlobals.response = EnvGlobals.requestSpecification.when().log().all().queryParams(queryParam,currencyId).get(endPoint);
    }


    public static String getResponsePath(String key) {
        return EnvGlobals.response.getBody().path(key, new String[0]) == null ? "" : EnvGlobals.response.getBody().path(key, new String[0]).toString();
    }

    public static void thenFunction(int statusCode) {
        EnvGlobals.response.then().log().all().statusCode(statusCode);
    }

    public static <K, V> Map<K, V> headers(Object... keyValues) {
        Map<K, V> map = new HashMap();

        for(int index = 0; index < keyValues.length / 2; ++index) {
            map.put((K) keyValues[index * 2], (V) keyValues[index * 2 + 1]);
        }

        return map;
    }

    public static void currencyConversion(String endPoint,String amount,String sourceCurrency){
        if (sourceCurrency.equals("BTC")){
            EnvGlobals.response = EnvGlobals.requestSpecification.when().log().all().queryParam("amount",amount).queryParam("convert", configProperties.conversionCurrency).queryParam("id",EnvGlobals.bitcoinId).get(endPoint);
        }
        else if (sourceCurrency.equals("USDT")){
            EnvGlobals.response = EnvGlobals.requestSpecification.when().log().all().queryParam("amount",amount).queryParam("convert",configProperties.conversionCurrency).queryParam("id",EnvGlobals.USDTid).get(endPoint);
        }
        else if (sourceCurrency.equals("ETH")){
            EnvGlobals.response = EnvGlobals.requestSpecification.when().log().all().queryParam("amount",amount).queryParam("convert",configProperties.conversionCurrency).queryParam("id",EnvGlobals.ethereumId).get(endPoint);
        }
    }

    public static String fetchParticularIds(String nameOfCurrency){
        JsonPath jsonPathEvaluator = EnvGlobals.response.jsonPath();
        id = jsonPathEvaluator.get("data");
        int actualCount = id.size();
        for (int i = 0 ;i<actualCount;i++){
            if (ReusableMethods.getResponsePath("data["+i+"].name").equals(nameOfCurrency)){
                EnvGlobals.cryptoId=ReusableMethods.getResponsePath("data["+i+"].id");
                break;
            }
        }
        return EnvGlobals.cryptoId;
    }

    public static void fetchIds(){
        Map<String, String> id;
        JsonPath jsonPathEvaluator = EnvGlobals.response.jsonPath();
        id=jsonPathEvaluator.get("data");
        EnvGlobals.actualDataCount = id.size();
    }

}

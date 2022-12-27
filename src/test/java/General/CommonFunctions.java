package General;

import MainCall.GlobalVariable;
import MainCall.configProperties;
import Utils.ExtentReport;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonFunctions {

    public static RequestSpecification REQUEST;

    public static void contentType(String contentType) {
        try {
            REQUEST = RestAssured.given().contentType(contentType);
            GlobalVariable.requestSpecification = REQUEST.given();
        } catch (Exception e) {
            ExtentReport.logFail(e);
        }
    }

    public static void givenHeaders(Map<String, String> headers) {
        try {
            contentType("application/json");
            GlobalVariable.requestSpecification = REQUEST.given().headers(headers);
        } catch (Exception e) {
            ExtentReport.logFail(e);
        }
    }


    public static void whenFunctionWithParams(String endPoint, String queryParam, String currencyId) {
        FilterableRequestSpecification filter= (FilterableRequestSpecification) GlobalVariable.requestSpecification;
        try {
            filter.removeQueryParam(queryParam);
            GlobalVariable.response = GlobalVariable.requestSpecification.when().log().all().queryParams(queryParam, currencyId).get(endPoint);
        } catch (Exception e) {
            ExtentReport.logFail(e);
        }

    }


    public static String getResponsePath(String key) {
        try {
            return GlobalVariable.response.getBody().path(key, new String[0]) == null ? "" : GlobalVariable.response.getBody().path(key, new String[0]).toString();
        } catch (Exception e) {
            ExtentReport.logFail(e);
        }
        return null;
    }

    public static void thenFunction(int statusCode) {
        try {
            GlobalVariable.response.then().log().all().statusCode(statusCode);
        } catch (Exception e) {
            ExtentReport.logFail(e);
        }

    }

    public static <K, V> Map<K, V> headers(Object... keyValues) {
        try {
            Map<K, V> map = new HashMap();

            for (int index = 0; index < keyValues.length / 2; ++index) {
                map.put((K) keyValues[index * 2], (V) keyValues[index * 2 + 1]);
            }

            return map;
        } catch (Exception e) {
            ExtentReport.logFail(e);
        }
        return null;
    }

    public static void currencyConversion(String endPoint, String amount, String sourceCurrency, String id) {
        try {
            FilterableRequestSpecification filter= (FilterableRequestSpecification) GlobalVariable.requestSpecification;
            if (sourceCurrency.equals("BTC")) {
                filter.removeQueryParam("amount");
                filter.removeQueryParam("convert");
                filter.removeQueryParam("id");
                GlobalVariable.response = GlobalVariable.requestSpecification.when().log().all().queryParam("amount", amount).queryParam("convert", configProperties.conversionCurrency).queryParam("id", id).get(endPoint);
            } else if (sourceCurrency.equals("USDT")) {
                filter.removeQueryParam("amount");
                filter.removeQueryParam("convert");
                filter.removeQueryParam("id");
                GlobalVariable.response = GlobalVariable.requestSpecification.when().log().all().queryParam("amount", amount).queryParam("convert", configProperties.conversionCurrency).queryParam("id", id).get(endPoint);
            } else if (sourceCurrency.equals("ETH")) {
                filter.removeQueryParam("amount");
                filter.removeQueryParam("convert");
                filter.removeQueryParam("id");
                GlobalVariable.response = GlobalVariable.requestSpecification.when().log().all().queryParam("amount", amount).queryParam("convert", configProperties.conversionCurrency).queryParam("id", id).get(endPoint);
            }
        } catch (Exception e) {
            ExtentReport.logFail(e);
        }
    }


    public static List fetchResponseByPath(String path) {
        try {
            JsonPath jsonPathEvaluator = GlobalVariable.response.jsonPath();
            List data = jsonPathEvaluator.get(path);
            if (data != null) {
                return data;
            } else if (data.size() == 0) {
                return null;
            }
        } catch (Exception e) {
            ExtentReport.logFail(e);
        }
        return null;
    }


}

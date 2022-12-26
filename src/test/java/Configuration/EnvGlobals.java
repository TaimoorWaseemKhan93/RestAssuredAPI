package Configuration;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class EnvGlobals {

    public static RequestSpecification requestSpecification;
    public static Response response;
    public static String bitcoinId;
    public static String USDTid;
    public static String ethereumId;
    public static String cryptoId;
    public static String currencyId="1,2,3,4,5,6,7,8,9,10";
    public static int actualDataCount;

}

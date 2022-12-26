package Validations;

import Configuration.EnvGlobals;
import General.ReusableMethods;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.List;

public class BackendValidations {
    public static List dataSize;

    public static void validateCryptoInfo(int id) {
        Assert.assertEquals("2015-08-07T00:00:00.000Z", ReusableMethods.getResponsePath("data." + EnvGlobals.ethereumId + ".date_added"));
        Assert.assertEquals("https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png", ReusableMethods.getResponsePath("data." + EnvGlobals.ethereumId + ".logo"));
        Assert.assertEquals("https://github.com/ethereum/wiki/wiki/White-Paper", ReusableMethods.getResponsePath("data." + EnvGlobals.ethereumId + ".urls.technical_doc[0]"));
        Assert.assertEquals("ETH", ReusableMethods.getResponsePath("data." + EnvGlobals.ethereumId + ".symbol"));
        JsonPath jsonPathEvaluator = EnvGlobals.response.jsonPath();
        dataSize = jsonPathEvaluator.get("data." + id + ".tags");
        int actualCount = dataSize.size();
        if (actualCount > 0) {
            for (int i = 0; i < actualCount; i++) {
                if (ReusableMethods.getResponsePath("data." + id + ".tags[" + i + "]").equals("mineable")) {
                    Assert.assertEquals(ReusableMethods.getResponsePath("data." + id + ".tags[" + i + "]"), "mineable");
                    break;
                } else if (i == (actualCount - 1)) {
                    Assert.fail("Validation Failed. Couldn't find Mineable tag");
                }
            }
        } else {
            Assert.fail("Validation Failed. Couldn't find Mineable tag");
        }

    }

    public static void validateFetchIds(int actualCount) {
        JsonPath jsonPathEvaluator = EnvGlobals.response.jsonPath();
        for (int i = 1; i <= actualCount; i++) {
            dataSize = jsonPathEvaluator.get("data." + i + ".tags");
            int tagsLength = dataSize.size();
            for (int j = 0; j < tagsLength; j++) {
                if (ReusableMethods.getResponsePath("data." + (i) + ".tags[" + j + "]").equals("mineable")) {
                    System.out.println(ReusableMethods.getResponsePath("data." + i + ".name") + " has mineable tag");
                    validateCurrencies(i);
                }

            }
        }
    }

    public static void validateCurrencies(int currencyId) {
        JsonPath jsonPathEvaluator = EnvGlobals.response.jsonPath();
        dataSize = jsonPathEvaluator.get("data." + currencyId + ".tags");
        int actualCount = dataSize.size();
        for (int i = 0; i < actualCount; i++) {
            if (ReusableMethods.getResponsePath("data." + currencyId + ".tags[" + i + "]").equals("mineable")) {
                break;
            } else {
                Assert.fail("Validation Failed. Couldn't find Mineable tag");
            }

        }
    }
}

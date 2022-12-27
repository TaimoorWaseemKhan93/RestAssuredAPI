package General;

import Utils.ExtentReport;
import org.testng.Assert;

public class Assertions {
    public void equal(String actual, String expected, String message){
        try{
            Assert.assertEquals(expected, actual);
            ExtentReport.logPass(message);
        }catch(Exception e){
            ExtentReport.logFail(e);
        }
    }
}

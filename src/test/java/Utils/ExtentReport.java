package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import MainCall.configProperties;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport {


    private static ExtentReports extent;
    private static ExtentSparkReporter spark;

    private static ExtentTest scenario;
    private static ExtentTest testSteps;
    private static String name = configProperties.path + (new SimpleDateFormat("dd-MM-yyyy HH-mm-ss")).format(new Date()) + ".html";

    public static Boolean testCaseStatus = true;

    private static Theme getTheme(){
        switch (configProperties.theme){
            case "Standard":
                return Theme.STANDARD;
            default:
                return Theme.STANDARD;
        }
    }

    public static void startReport(){
        if(extent == null){
            extent = new ExtentReports();
            spark = new ExtentSparkReporter(name);
            spark.config().setTheme(getTheme());
            spark.config().setDocumentTitle(configProperties.documentationTitle);
            spark.config().setReportName(configProperties.reportName);
            extent.attachReporter(spark);
        }
    }

    public static void getReport(){
        extent.flush();
    }

    public static void createFeature(String scn){
        scenario = extent.createTest(scn);
    }

    public static void createTestSteps(String testStep){
        testSteps = scenario.createNode(testStep);
    }

    public static void logPass(String log){
        testSteps.createNode(log).pass("Pass");
    }

    public static void logFail(String log){
        testSteps.createNode(log).fail("Fail");
        testCaseStatus = false;
    }

    public static void logFail(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        testSteps.createNode(sw.toString()).fail("Fail");
        testCaseStatus = false;
    }

}

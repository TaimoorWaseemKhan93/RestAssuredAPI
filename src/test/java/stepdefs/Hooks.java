package stepdefs;

import Utils.ExtentReport;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.*;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import org.testng.Assert;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class Hooks {
    private int stepNumber = 0;

    //This will initiate and start the report before starting suite
    @BeforeAll
    public static void beforeAll() {
        ExtentReport.startReport();
    }

    //This will pass the log of feature file and update logs
    @Before
    public static void before(Scenario s){
        ExtentReport.createFeature(s.getName());
    }

    //This will execute and add every step in extend report
    @BeforeStep
    public void beforeStep(Scenario scenario){
        String currentStepText = "No Step defined";

        try {

            Field delegate = scenario.getClass().getDeclaredField("delegate");
            delegate.setAccessible(true);
            TestCaseState testCaseState = (TestCaseState) delegate.get(scenario);

            Field testCaseField = testCaseState.getClass().getDeclaredField("testCase");
            testCaseField.setAccessible(true);
            TestCase testCase = (TestCase) testCaseField.get(testCaseState);
            Thread.sleep(1000);

            List<PickleStepTestStep> testStepTitles = testCase.getTestSteps()
                    .stream()
                    .filter(step -> step instanceof PickleStepTestStep)
                    .map(step -> (PickleStepTestStep) step)
                    .collect(Collectors.toList());

            currentStepText = testStepTitles.get(stepNumber).getStepText();

            ExtentReport.createTestSteps(currentStepText);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //This will add one line after every step.
    @AfterStep
    public void afterStep(){
        stepNumber +=1;
    }

    //This will run when any case fails.
    @After
    public void after(){
        if(ExtentReport.testCaseStatus == false){
            Assert.fail();
        }
    }

    //This will get all the logs of whole cases.
    @AfterAll
    public static void after_all() {
        ExtentReport.getReport();
    }
}

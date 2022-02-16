package stepDefinitions;

import apiEngine.Endpoints3;
import cucumber.TestContext;

public class BaseStep {
    private static final String BASE_URL = "https://bookstore.toolsqa.com";
    private Endpoints3 endPoints;

    public BaseStep(TestContext testContext) {
    	endPoints = testContext.getEndPoints();
    }

    public Endpoints3 getEndPoints() {
        return endPoints;
    }
}
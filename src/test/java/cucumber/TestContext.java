package cucumber;

import apiEngine.Endpoints3;

public class TestContext {
	
	private String BASE_URL = "https://bookstore.toolsqa.com";
	private Endpoints3 endPoints;
	
	public TestContext() {
		endPoints = new Endpoints3(BASE_URL);
	}
	
	 public Endpoints3 getEndPoints() {
        return endPoints;
    }
}
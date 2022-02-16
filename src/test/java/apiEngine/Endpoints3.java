package apiEngine;

import org.apache.http.HttpStatus;

import apiEngine.model.requests.AddBooksRequest;
import apiEngine.model.requests.AuthorizationRequest;
import apiEngine.model.requests.RemoveBookRequest;
import apiEngine.model.responses.Book;
import apiEngine.model.responses.Books;
import apiEngine.model.responses.Token;
import apiEngine.model.responses.UserAccount;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Endpoints3 {
	private static RequestSpecification request = null;

	public Endpoints3(String baseUrl) {
		RestAssured.baseURI = baseUrl;
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
	}

	public void authenticateUser(AuthorizationRequest authRequest) {
		Response response = request.body(authRequest).post(Route.generateToken());
		if (response.statusCode() != HttpStatus.SC_OK) {
			throw new RuntimeException("Authentication Failed. Content of failed Response: " + response.toString()
					+ " , Status Code : " + response.statusCode());
		}
		Token tokenResponse = response.body().jsonPath().getObject("$", Token.class);
		System.out.println("Token is : " + tokenResponse.token);
		request.header("Authorization", "Bearer " + tokenResponse.token);
	}

	public static IRestResponse<Books> getBooks() {
		Response response = request.get(Route.books());
		return new RestResponse(Books.class, response);
	}

	public static RestResponse<Books> addBook(AddBooksRequest addBooksRequest) {
		Response response = request.body(addBooksRequest).post(Route.books());
		return new RestResponse(Books.class, response);
	}

	public static Response removeBook(RemoveBookRequest removeBookRequest) {
		return request.body(removeBookRequest).delete(Route.book());
	}

	public static RestResponse<UserAccount> getUserAccount(String userId) {
		Response response = request.get(Route.userAccount(userId));
		return new RestResponse(UserAccount.class, response);
	}

}
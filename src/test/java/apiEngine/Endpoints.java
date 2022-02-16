//package apiEngine;
//
//import apiEngine.model.requests.AddBooksRequest;
//import apiEngine.model.requests.AuthorizationRequest;
//import apiEngine.model.requests.RemoveBookRequest;
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//
//public class Endpoints {
//	private static final String BASE_URL = "https://bookstore.toolsqa.com";
//
//	public static Response authenticateUser(AuthorizationRequest authRequest) {
//		RequestSpecification request = partialRequest();
//		Response response = request.body(authRequest).post(Route.generateToken());
//		return response;
//	}
//
//	public static Response getBooks() {
//		RequestSpecification request = partialRequest();
//		Response response = request.get(Route.books());
//		return response;
//	}
//
//	public static Response addBook(AddBooksRequest addBooksRequest, String token) {
//		RequestSpecification request = partialRequest();
//		request.header("Authorization", "Bearer " + token);
//		Response response = request.body(addBooksRequest).post(Route.books());
//		return response;
//	}
//
//	public static Response removeBook(RemoveBookRequest removeBookRequest, String token) {
//		RequestSpecification request = partialRequest();
//		request.header("Authorization", "Bearer " + token);
//		Response response = request.body(removeBookRequest).delete(Route.book());
//		return response;
//	}
//
//	public static Response getUserAccount(String userId, String token) {
//		RequestSpecification request = partialRequest();
//		request.header("Authorization", "Bearer " + token);
//		Response response = request.get(Route.userAccount(userId));
//		return response;
//	}
//
//	
//	
//	
//	public static RequestSpecification partialRequest() {
//		RestAssured.baseURI = BASE_URL;
//		RequestSpecification request = RestAssured.given();
//		request.header("Content-Type", "application/json");
//		return request;
//	}
//}
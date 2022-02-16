//package apiEngine;
//
//import apiEngine.model.requests.AddBooksRequest;
//import apiEngine.model.requests.AuthorizationRequest;
//import apiEngine.model.requests.RemoveBookRequest;
//import apiEngine.model.responses.Book;
//import apiEngine.model.responses.Books;
//import apiEngine.model.responses.Token;
//import apiEngine.model.responses.UserAccount;
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//
//public class Endpoints2 {
//	private static final String BASE_URL = "https://bookstore.toolsqa.com";
//
//	public static IRestResponse<Token> authenticateUser(AuthorizationRequest authRequest) {
//		RequestSpecification request = partialRequest();
//		Response response = request.body(authRequest).post(Route.generateToken());
//		return new RestResponse(Token.class, response);
//	}
//
//	public static RestResponse<Books> getBooks() {
//		RequestSpecification request = partialRequest();
//		Response response = request.get(Route.books());
//		return new RestResponse(Books.class, response);
//	}
//
//	public static RestResponse<Books> addBook(AddBooksRequest addBooksRequest, String token) {
//		RequestSpecification request = partialRequest();
//		request.header("Authorization", "Bearer " + token);
//		Response response = request.body(addBooksRequest).post(Route.books());
//		return new RestResponse(Books.class, response);
//	}
//
//	public static Response removeBook(RemoveBookRequest removeBookRequest, String token) {
//
//		RequestSpecification request = partialRequest();
//        request.header("Authorization", "Bearer " + token);
//        return request.body(removeBookRequest).delete(Route.book());
//    }
//
//	public static RestResponse<UserAccount> getUserAccount(String userId, String token) {
//		RequestSpecification request = partialRequest();
//		request.header("Authorization", "Bearer " + token);
//		Response response = request.get(Route.userAccount(userId));
//		return new RestResponse(UserAccount.class, response);
//	}
//
//	
//	
//	
//	
//	
//	
//	
//	
//	
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
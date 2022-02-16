//package stepDefinitions;
//
//import org.junit.Assert;
//
//import apiEngine.Endpoints;
//import apiEngine.model.requests.AddBooksRequest;
//import apiEngine.model.requests.AuthorizationRequest;
//import apiEngine.model.requests.ISBN;
//import apiEngine.model.requests.RemoveBookRequest;
//import apiEngine.model.responses.Book;
//import apiEngine.model.responses.Books;
//import apiEngine.model.responses.Token;
//import apiEngine.model.responses.UserAccount;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//
//public class Steps2 {
//	private static final String USER_ID = "54a21573-f256-4703-8a96-547e866ed5e0";
//	private static final String USERNAME = "neha";
//	private static final String PASSWORD = "NehaSarda1!";
//	private static final String BASE_URL = "https://bookstore.toolsqa.com";
//
//	private static String token;
//	private static String jsonString;
//	private static String bookId;
//
//	private static Response response;
//	private static Token tokenResponse;
//	private static Book book;
//
//	@Given("I am an authorized user")
//	public void iAmAnAuthorizedUser() {
//
//		AuthorizationRequest authorizationRequest = new AuthorizationRequest(USERNAME, PASSWORD);
//		response = Endpoints.authenticateUser(authorizationRequest);
//		tokenResponse = response.getBody().as(Token.class);
//
//		System.out.println("Token is : " + tokenResponse.token);
//	}
//
//	@Given("A list of books are available")
//	public void listOfBooksAreAvailable() {
//
//		response = Endpoints.getBooks();
//		Books books = response.getBody().as(Books.class);
//		book = books.books.get(0);
//		System.out.println("BookID is : " + book.isbn);
//	}
//
//	@When("I add a book to my reading list")
//	public void addBookInList() {
//		ISBN isbn = new ISBN(book.isbn);
//		AddBooksRequest addBooksRequest = new AddBooksRequest(USER_ID, isbn);
//		response = Endpoints.addBook(addBooksRequest, tokenResponse.token);
//	}
//
//	@Then("the book is added")
//	public void bookIsAdded() {
//		Assert.assertEquals(201, response.getStatusCode());
//	}
//
//	@When("I remove a book from my reading list")
//	public void removeBookFromList() {
//		RemoveBookRequest removeBookRequest = new RemoveBookRequest(USER_ID, book.isbn);
//		RestAssured.baseURI = BASE_URL;
//		response = Endpoints.removeBook(removeBookRequest, tokenResponse.token);
//
//
//	}
//
//	@Then("the book is removed")
//	public void bookIsRemoved() {
//		Assert.assertEquals(204, response.getStatusCode());
//		response = Endpoints.getUserAccount(USER_ID, tokenResponse.token);
//		Assert.assertEquals(200, response.getStatusCode());
//		UserAccount userAccount = response.getBody().as(UserAccount.class);
//        Assert.assertEquals(0, userAccount.books.size());
//	}
//}
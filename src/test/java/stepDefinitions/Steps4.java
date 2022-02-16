//package stepDefinitions;
//
//import org.junit.Assert;
//
//import apiEngine.Endpoints3;
//import apiEngine.IRestResponse;
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
//import io.restassured.response.Response;
//
//public class Steps4 {
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
//	private static IRestResponse<UserAccount> userAccountResponse;
//	private static IRestResponse<Books> booklist;
//	private static Endpoints3 endPoints;
//
//	@Given("I am an authorized user")
//	public void iAmAnAuthorizedUser() {
//		endPoints = new Endpoints3(BASE_URL);
//		AuthorizationRequest authorizationRequest = new AuthorizationRequest(USERNAME, PASSWORD);
//		endPoints.authenticateUser(authorizationRequest);
//	}
//
//	@Given("A list of books are available")
//	public void listOfBooksAreAvailable() {
//
//		IRestResponse<Books> booksResponse = endPoints.getBooks();
//		book = booksResponse.getBody().books.get(0);
//		System.out.println("BookID is : " + book.isbn);
//	}
//
//	@When("I add a book to my reading list")
//	public void addBookInList() {
//		ISBN isbn = new ISBN(book.isbn);
//		AddBooksRequest addBooksRequest = new AddBooksRequest(USER_ID, isbn);
//		booklist = endPoints.addBook(addBooksRequest);
//	}
//
//	@Then("the book is added")
//	public void bookIsAdded() {
//		Assert.assertTrue(booklist.isSuccessful());
//		Assert.assertEquals(201, booklist.getStatusCode());
//		Assert.assertEquals(book.isbn, booklist.getBody().books.get(0).isbn);
//	}
//	
//
//	@When("I remove a book from my reading list")
//	public void removeBookFromList() {
//		RemoveBookRequest removeBookRequest = new RemoveBookRequest(USER_ID, book.isbn);
//		response = endPoints.removeBook(removeBookRequest);
//	}
//
//	@Then("the book is removed")
//	public void bookIsRemoved() {
//		Assert.assertEquals(204, response.getStatusCode());
//		userAccountResponse  = endPoints.getUserAccount(USER_ID);
//		Assert.assertEquals(200, userAccountResponse.getStatusCode());
//        Assert.assertEquals(0, userAccountResponse.getBody().books.size());
//	}
//}
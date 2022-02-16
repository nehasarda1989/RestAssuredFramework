package stepDefinitions;

import org.junit.Assert;

import apiEngine.IRestResponse;
import apiEngine.model.requests.AddBooksRequest;
import apiEngine.model.requests.ISBN;
import apiEngine.model.requests.RemoveBookRequest;
import apiEngine.model.responses.Book;
import apiEngine.model.responses.Books;
import apiEngine.model.responses.UserAccount;
import cucumber.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class BooksSteps extends BaseStep {

    public BooksSteps(TestContext testContext){
    	super(testContext);
    }

    private final String USER_ID = "54a21573-f256-4703-8a96-547e866ed5e0";
    private Response response;
    private IRestResponse<UserAccount> userAccountResponse;
    private static IRestResponse<Books> booklist;
    private Book book;

    @Given("^A list of books are available$")
    public void listOfBooksAreAvailable() {
        IRestResponse<Books> booksResponse = getEndPoints().getBooks();
        book = booksResponse.getBody().books.get(0);
    }

    @When("^I add a book to my reading list$")
    public void addBookInList() {
        ISBN isbn = new ISBN(book.isbn);
        AddBooksRequest addBooksRequest = new AddBooksRequest(USER_ID, isbn);
        booklist = getEndPoints().addBook(addBooksRequest);
    }

    @Then("^the book is added$")
    public void bookIsAdded() {
    	Assert.assertTrue(booklist.isSuccessful());
		Assert.assertEquals(201, booklist.getStatusCode());
		Assert.assertEquals(book.isbn, booklist.getBody().books.get(0).isbn);
    }

    @When("^I remove a book from my reading list$")
    public void removeBookFromList() {
        RemoveBookRequest removeBookRequest = new RemoveBookRequest(USER_ID, book.isbn);
        response = getEndPoints().removeBook(removeBookRequest);
    }

    @Then("^the book is removed$")
    public void bookIsRemoved() {
        Assert.assertEquals(204, response.getStatusCode());

        userAccountResponse = getEndPoints().getUserAccount(USER_ID);
        Assert.assertEquals(200, userAccountResponse.getStatusCode());

        Assert.assertEquals(0, userAccountResponse.getBody().books.size());
    }
}
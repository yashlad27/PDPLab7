import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Junit for testing the methods of Book.java class.
 */

public class BookTest {
  /**
   * two variables declared for Book and Person.
   */
  private Book malgudi;
  private Person yash;

  /**
   * initialising of values.
   */

  @Before
  public void setUp() {

    yash = new Person("Yash", "Lad", 2002);
    malgudi = new Book("Malgudi", yash, 10.88f);
  }

  @Test
  public void testTitle() {
    assertEquals("The Title should match.", "Malgudi",
            malgudi.getTitle());
  }

  @Test
  public void testPrice() {
    assertEquals("The price of book should match.", 10.88f,
            10.88f, malgudi.getPrice());
  }

  @Test
  public void testAuthor() {
    assertNotNull("Author's value should not be NULL", malgudi.getAuthor());
    assertEquals("Author's value should match.", yash, malgudi.getAuthor());
  }

  @Test
  public void testInvalidPrice() {
    Book invalidBook = new Book("TestBook1", yash, 0.0f);
    assertEquals("Invalid price should be set to 0", 0.0f,
            invalidBook.getPrice(), 0.01f);
  }

  @Test
  public void testEmptyTitle() {
    Book emptyTitleBook = new Book("Untitled", yash, 10.88f);
    assertEquals("title should default to 'Untitled'.", "Untitled",
            emptyTitleBook.getTitle());
  }

}
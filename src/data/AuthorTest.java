package src.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class AuthorTest {

    @Test
    public void setLink() {
        Author author = new Author("Harry Potter");
        author.setLink("abcd");
        assertEquals(author.getLink(), "abcd");
    }

    @Test
    public void getName() {
        Author author = new Author("Harry Potter");
        assertEquals("Harry Potter", author.getName());
    }

    @Test
    public void getArtworks() {
        Author author = new Author("Harry Potter");
        assertEquals(author.getArtworks().size(), 0);
    }

    @Test
    public void compareTo() {
        Author author = new Author("Harry Potter");
        assertEquals(0, author.compareTo(author));
    }
}
package src.trie;

import org.junit.Test;

import static org.junit.Assert.*;

public class TermTest {


    @Test
    public void compareTo() {
        Term term1 = new Term("A");
        Term term2 = new Term("A");
        assertEquals(0, term1.compareTo(term2));
    }

    @Test
    public void testToString() {
        Term term1 = new Term("A");
        assertEquals("A", term1.toString());
    }

}
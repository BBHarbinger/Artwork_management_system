package src.trie;

import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void setTerm() {
        Term term2 = new Term("ABC");
        Node node = new Node("C");
        node.setTerm(term2);
        assertEquals(node.getTerm().getTerm(), "ABC");
    }

    @Test
    public void setReferences() {
        Node[] reference = new Node[20];
        Node node = new Node();
        node.setReferences(reference);
        assertEquals(reference, node.getReferences());
    }
}
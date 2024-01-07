package src.trie;

/**
 * @author Harry Smith
 */

public class Node {

    private int characterNumbers = 128;

    private Term term;
    private int words;
    private int prefixes;
    private Node[] references;

    /**
     * Initialize a Node with an empty string and 0 weight; useful for
     * writing tests.
     */
    public Node() {
        // TODO!
        this.term = new Term("");
        this.words = 0;
        this.prefixes = 0;
        this.references = new Node[characterNumbers];
        for (int i = 0; i < characterNumbers; i++) {
            this.references[i] = null;
        }
    }

    /**
     * Initialize a Node with the given query string and weight.
     * @throws IllegalArgumentException if query is null or if weight is negative.
     */
    public Node(String query) {
        if (query == null) {
            throw new IllegalArgumentException();
        }
        this.term = new Term(query);
        this.words = 0;
        this.prefixes = 0;
        this.references = new Node[characterNumbers];
        for (int i = 0; i < characterNumbers; i++) {
            this.references[i] = null;
        }
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public int getWords() {
        return words;
    }

    public void setWords(int words) {
        this.words = words;
    }

    public int getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(int prefixes) {
        this.prefixes = prefixes;
    }

    public Node[] getReferences() {
        return this.references;
    }

    public void setReferences(Node[] references) {
        this.references = references;
    }
}

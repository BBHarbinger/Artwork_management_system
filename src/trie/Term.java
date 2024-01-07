package src.trie;

public class Term {

    private String term = null;

    /**
     * Initialize a Term with a given query String and weight
     */
    public Term(String term) {
        if (term == null) {
            throw new IllegalArgumentException();
        }
        this.term = term;
    }


    public int compareTo(Term that) {
        return this.term.compareTo(that.getTerm());
    }


    public String toString() {
        return this.term;
    }

    public String getTerm() {
        return this.term;
    }

    public void setTerm(String term) { // why setTerm needs to return String?
        this.term = term;
    }

}

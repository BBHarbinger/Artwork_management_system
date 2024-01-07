package src.trie;

import src.data.Artwork;
import src.data.Author;
import src.data.Data;

import java.util.*;

/**
 * This is the class to construct trie.
 * The tries for artworks and authors are a bit different.
 * I suggest you build trie for strings.
 * After you get results, you can use two maps in
 * Data to map artwork title or author back to its instance.
 * Shouldn't be difficult.
 */
public class Trie {


    /**
     * This is a function to build trie for selected artwork names,
     * and will return a list of matching artwork objects.
     * @param rowIndices
     * @param data
     * @param prefix
     * @return
     */
    public static TreeSet<Artwork> trieForArtwork(
            Set<Integer> rowIndices, Data data, String prefix) {

        // check whether prefix is valid or not.
        for (int i = 0; i < prefix.length(); i++) {
            // check whether a char is valid
            if (prefix.charAt(i) >= 128) {
                return new TreeSet<>();
            }
        }

        Collection<String> collection = new ArrayList<>();

        for (Integer index: rowIndices) {
            String title = data.getArtworkList().get(index).getTitle();
            collection.add(title);
        }

        List<String> resultsString = buildTrieAndGetSuggestions(collection, prefix.toLowerCase());
        Map<String, Set<Artwork>> map = data.getArtworkTitleToObjectSets();
        TreeSet<Artwork> artworks = new TreeSet<>();
        for (String str: resultsString) {
            Set<Artwork> artworkSet = map.get(str.toLowerCase());
            for (Artwork artwork: artworkSet) {
                artworks.add(artwork);
            }
        }
        return artworks;
    }

    /**
     * This is a function to build trie for all author names.
     * @param prefix the prefix to match.
     * @param data the Data object
     * @return a list of matching author.
     */
    public static TreeSet<Author> trieForAuthor(String prefix, Data data) {

        // check whether prefix is valid or not.
        for (int i = 0; i < prefix.length(); i++) {
            // check whether a char is valid
            if (prefix.charAt(i) > 128) {
                return new TreeSet<>();
            }
        }

        Collection<String> collection = new ArrayList<>();

        for (String author: data.getAuthorNameToObject().keySet()) {
            collection.add(author);
        }

        List<String> resultsString = buildTrieAndGetSuggestions(collection, prefix);
        TreeSet<Author> authors = new TreeSet<>();
        Map<String, Author> authorNameToObject = data.getAuthorNameToObject();
        for (String str: resultsString) {
            authors.add(authorNameToObject.get(str.toLowerCase()));
        }

        return authors;
    }


    public static List<String> buildTrieAndGetSuggestions(Collection<String> terms, String prefix) {
        Node root = new Node("");

        for (String str: terms) {
            addWord(root, str.toLowerCase());
        }

        List<String> list = new ArrayList<>();

        root = getSubTrie(prefix.toLowerCase(), root);
        if (root == null) {
            return list;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (!node.getTerm().getTerm().equals("")) {
                list.add(node.getTerm().getTerm());
            }
            for (int j = 0; j < 128; j++) {
                if (node.getReferences()[j] != null) {
                    queue.offer(node.getReferences()[j]);
                }
            }
        }
        Collections.sort(list);
        return list;
    }


    public static Node getSubTrie(String prefix, Node root) {

        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int index = c;
            Node next = cur.getReferences()[index];
            if (next == null) {
                return null;
            } else {
                cur = next;
            }
        }
        return cur;
    }



    /**
     * Adds a new word with its associated weight to the Trie.
     * If the word contains an invalid character, simply do nothing.
     *
     * @param word the word to be added to the Trie
     */
    public static void addWord(Node root, String word) {

        // navigate through the trie
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            cur.setPrefixes(cur.getPrefixes() + 1);
            char c = word.charAt(i);
            int index = c;
            if (cur.getReferences()[index] == null) {
                // no children
                // create a new node
                Node newNode = new Node();
                cur.getReferences()[index] = newNode;
                cur = cur.getReferences()[index];
            } else {
                // has children of the specific char
                cur = cur.getReferences()[index];
            }
        }
        // deal with the last node
        cur.setWords(cur.getWords() + 1);
        cur.setPrefixes(cur.getPrefixes() + 1);
        Term term = cur.getTerm();
        term.setTerm(word);
    }

}

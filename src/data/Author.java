package src.data;

import java.util.TreeSet;

/**
 * This is a class to represent authors.
 */
public class Author implements Comparable<Author> {
    /**
     * Author's name.
     */
    private String name;



    /**
     * A link to a webpage introducing the artist.
     */
    private String link;

    /**
     * A list of artworks created by this author.
     */
    private TreeSet<Artwork> artworks;


    /**
     * Constructor.
     * @param name author's name.
     */
    public Author(String name) {
        this.name = name;
        this.artworks = new TreeSet<>();
    }


    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public TreeSet<Artwork> getArtworks() {
        return artworks;
    }

    @Override
    public int compareTo(Author o) {
        return this.name.compareTo(o.getName());
    }
}

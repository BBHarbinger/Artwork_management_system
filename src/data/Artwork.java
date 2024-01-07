package src.data;

import java.util.List;
import java.util.Objects;


/**
 * This is class to represent artworks.
 */
public class Artwork implements Comparable {
    /**
     * Title of the artwork.
     */
    private String title = null;

    /**
     * Author of the artwork.
     */
    private String author = null;

    /**
     * Genre of the artwork.
     * For example, Post-Impressionism...
     */
    private String genre = null;

    /**
     * This variable represents the century
     *  in which the artwork was created.
     */
    private int centuery = 0;

    /**
     * Theme of the artwork,
     * such as Portrait.
     */
    private String topic = null;

    /**
     * The medium of the artwork,
     * such as oil, tempera...
     */
    private String medium = null;

    /**
     * A link to the wikipedia.
     */
    private String wikiLink = null;

    /**
     * One sentence short description about the artwork.
     */
    private String shortDescription = null;

    /**
     * A list of closest neighbors for recommendation.
     */
    private List<Artwork> closestNeighbors = null;


    /**
     * Constructor
     * @param title
     * @param author
     * @param genre
     * @param centuery
     * @param theme
     * @param medium
     * @param wikiLink a link to wikipedia page.
     * @param shortDescription
     */
    public Artwork(String title, String author, String genre,
                   int centuery, String theme, String medium,
                   String wikiLink, String shortDescription) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.centuery = centuery;
        this.topic = theme;
        this.medium = medium;
        this.wikiLink = wikiLink;
        this.shortDescription = shortDescription;
    }

    /**
     * Two parameter constructor
     * @param title
     * @param author
     */
    public Artwork(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Artwork artwork = (Artwork) o;
        return Objects.equals(title, artwork.title) && Objects.equals(author, artwork.author);
    }



    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getCentury() {
        return centuery;
    }

    public String getTopic() {
        return topic;
    }

    public String getMedium() {
        return medium;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public List<Artwork> getClosestNeighbors() {
        return closestNeighbors;
    }

    public void setClosestNeighbors(List<Artwork> closestNeighbors) {
        this.closestNeighbors = closestNeighbors;
    }

    @Override
    public int compareTo(Object o) {
        // cast object to artwork
        Artwork o2 = (Artwork) o;
        // first compare title
        int titleCompare = this.title.compareTo(o2.getTitle());

        if (titleCompare == 0) {
            // if title is the same
            // compare author
            return this.author.compareTo(o2.getAuthor());
        }

        return titleCompare;
    }
}
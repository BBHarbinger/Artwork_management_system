package src.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArtworkTest {

    @Test
    public void testEquals() {
        Artwork artwork1 = new Artwork("Starry Night", "Van Gogh");
        Artwork artwork2 = new Artwork("Dream", "Picasso");
        assertEquals(artwork1, artwork1);
        assertNotEquals(artwork1, artwork2);
    }


    @Test
    public void getTitle() {
        Artwork artwork1 = new Artwork("Starry Night", "Van Gogh");
        assertEquals(artwork1.getTitle(), "Starry Night");
    }

    @Test
    public void getAuthor() {
        Artwork artwork1 = new Artwork("Starry Night", "Van Gogh");
        assertEquals(artwork1.getAuthor(), "Van Gogh");
    }

    @Test
    public void getGenre() {
        Artwork artwork1 = new Artwork("Starry Night", "Van Gogh",
                "Modern", 18, "Park Theme", "Oil", "", "");
        assertEquals(artwork1.getGenre(), "Modern");
    }

    @Test
    public void getCentuery() {
        Artwork artwork1 = new Artwork("Starry Night", "Van Gogh",
                "Modern", 18, "Park Theme", "Oil", "", "");
        assertEquals(artwork1.getCentury(), 18);
    }

    @Test
    public void getTopic() {
        Artwork artwork1 = new Artwork("Starry Night", "Van Gogh",
                "Modern", 18, "Park Theme", "Oil", "", "");
        assertEquals(artwork1.getTopic(), "Park Theme");
    }

    @Test
    public void getMedium() {
        Artwork artwork1 = new Artwork("Starry Night", "Van Gogh",
                "Modern", 18, "Park Theme", "Oil", "", "");
        assertEquals(artwork1.getMedium(), "Oil");
    }

    @Test
    public void getWikiLink() {
        Artwork artwork1 = new Artwork("Starry Night", "Van Gogh",
                "Modern", 18, "Park Theme", "Oil", "", "");
        assertEquals(artwork1.getWikiLink(), "");
    }

    @Test
    public void getShortDescription() {
        Artwork artwork1 = new Artwork("Starry Night", "Van Gogh",
                "Modern", 18, "Park Theme", "Oil", "", "");
        assertEquals(artwork1.getShortDescription(), "");
    }

    @Test
    public void getClosestNeighbors() {
        Artwork artwork1 = new Artwork("Starry Night", "Van Gogh",
                "Modern", 18, "Park Theme", "Oil", "", "");
        assertEquals(artwork1.getClosestNeighbors(), null);
    }

    @Test
    public void compareTo() {
        Artwork artwork1 = new Artwork("Starry Night", "Van Gogh",
                "Modern", 18, "Park Theme", "Oil", "", "");
        assertEquals(0, artwork1.compareTo(artwork1));
    }
}
package src.data;

import static org.junit.Assert.*;

public class DataTest {

    @org.junit.Test
    public void load() {
        Data data = new Data();
        data.load("src/data/datasetForTest.csv");

        // test artwork list
        assertEquals(6, data.getArtworkList().size());
        assertEquals("A Sunday Afternoon on the Island of La Grande Jatte",
                data.getArtworkList().get(0).getTitle());
        assertEquals("A Sunday Afternoon on the Island of La Grande Jatte",
                data.getArtworkList().get(1).getTitle());
        assertEquals("Campbell's Soup Cans",
                data.getArtworkList().get(3).getTitle());

        // test genre map
        assertEquals(4, data.getGenreMap().get("Post-Impressionism").size());
        assertTrue(data.getGenreMap().get("Post-Impressionism").contains(0));
        assertTrue(data.getGenreMap().get("Post-Impressionism").contains(1));

        // test authorNameToObject
        assertTrue(data.getAuthorNameToObject().containsKey("nobody"));
        assertEquals(2,
                data.getAuthorNameToObject().get("nobody").getArtworks().size());

        assertTrue(data.getAuthorNameToObject().get("nobody").getArtworks().
                contains(new Artwork("A Sunday Afternoon on the Island of La Grande Jatte",
                        "Nobody")));

        assertTrue(data.getAuthorNameToObject().get("nobody").getArtworks().
                contains(new Artwork("Holy Crap", "Nobody")));


        // test Map<String, Set<Artwork>> artworkTitleToObjectSets
        assertEquals(2,
                data.getArtworkTitleToObjectSets().
                        get("a sunday afternoon on the island of la grande jatte").size());
        assertTrue(data.getArtworkTitleToObjectSets().
                get("a sunday afternoon on the island of la grande jatte").
                contains(data.getArtworkList().get(0)));
        assertTrue(data.getArtworkTitleToObjectSets().
                get("a sunday afternoon on the island of la grande jatte").
                contains(data.getArtworkList().get(1)));

        // test Map<Integer, Set<Integer>> artworkTitleFirstLetterMap
        assertEquals(5, data.getArtworkTitleFirstLetterMap().get(1).size());
        assertTrue(data.getArtworkTitleFirstLetterMap().get(1).contains(0));
        assertTrue(data.getArtworkTitleFirstLetterMap().get(1).contains(1));
        assertTrue(data.getArtworkTitleFirstLetterMap().get(1).contains(2));
        assertTrue(data.getArtworkTitleFirstLetterMap().get(1).contains(3));
        assertTrue(data.getArtworkTitleFirstLetterMap().get(1).contains(4));
        assertEquals(1, data.getArtworkTitleFirstLetterMap().get(2).size());
        assertTrue(data.getArtworkTitleFirstLetterMap().get(2).contains(5));

        // test Map<Integer, Set<Integer>> artworkAuthorFirstLetterMap
        assertEquals(3, data.getArtworkAuthorFirstLetterMap().get(1).size());
        assertTrue(data.getArtworkAuthorFirstLetterMap().get(1).contains(0));
        assertTrue(data.getArtworkAuthorFirstLetterMap().get(1).contains(2));
        assertTrue(data.getArtworkAuthorFirstLetterMap().get(1).contains(3));

        assertEquals(3, data.getArtworkAuthorFirstLetterMap().get(2).size());
        assertTrue(data.getArtworkAuthorFirstLetterMap().get(2).contains(1));
        assertTrue(data.getArtworkAuthorFirstLetterMap().get(2).contains(4));
        assertTrue(data.getArtworkAuthorFirstLetterMap().get(2).contains(5));

        assertEquals(data.getKeywordArtworkMap().size(), data.getKeywordAuthorMap().size());
        assertTrue(data.getKeywordArtworkMap().
                get("island").contains(data.getArtworkList().get(1)));
        assertTrue(data.getKeywordArtworkMap().get("island").
                contains(data.getArtworkList().get(0)));

    }
}
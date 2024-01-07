package src.data;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;


public class RecommenderTest {

    @Test
    public void getAuthorAverageCenturyMap() {
        Data data = new Data();
        data.load("src/data/dataset.csv");
        Recommender rec = new Recommender();
        Map<String, Double> i = rec.getAuthorAverageCenturyMap(data);
        assertEquals(20, i.get("Pablo Picasso"), 0.5);
    }

    @Test
    public void getGenreAverageCenturyMap() {
        Data data = new Data();
        data.load("src/data/dataset.csv");
        Recommender rec = new Recommender();
        Map<String, Double> i = rec.getGenreAverageCenturyMap(data);
        assertEquals(20, i.get("Cubism"), 0.5);
    }

    @Test
    public void getThemeAverageCenturyMap() {
        Data data = new Data();
        data.load("src/data/dataset.csv");
        Recommender rec = new Recommender();
        Map<String, Double> i = rec.getGenreAverageCenturyMap(data);
        assertEquals(17, i.get("Baroque"), 0.5);
    }

    @Test
    public void getMediumAverageCenturyMap() {
        Data data = new Data();
        data.load("src/data/dataset.csv");
        Recommender rec = new Recommender();
        Map<String, Double> i = rec.getMediumAverageCenturyMap(data);
        assertEquals(18, i.get("Oil"), 0.5);
    }

    @Test
    public void build() {
        Data data = new Data();
        data.load("src/data/dataset.csv");
        Recommender rec = new Recommender();
        rec.build(data,9);
        assertEquals(data.getArtworkList().get(0).getClosestNeighbors().get(0).getTitle(),
                "Great Red Dragon and the Woman Clothed with the Sun");
    }
}
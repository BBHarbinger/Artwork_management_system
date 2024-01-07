package src.trie;

import org.junit.Test;
import src.data.Artwork;
import src.data.Author;
import src.data.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class TrieTest {

    @Test
    public void trieForArtwork() {
        Data data = new Data();
        data.load("src/data/datasetForTest.csv");
        Set<Integer> rowIndices = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            rowIndices.add(i);
        }

        TreeSet<Artwork> artworks = Trie.trieForArtwork(rowIndices, data, "");
        assertEquals(6, artworks.size());

        artworks = Trie.trieForArtwork(data.getArtworkTitleFirstLetterMap().get(1), data, "");
        assertEquals(5, artworks.size());

        artworks = Trie.trieForArtwork(data.getArtworkTitleFirstLetterMap().get(1), data, "A");
        assertEquals(2, artworks.size());

        artworks = Trie.trieForArtwork(data.getArtworkTitleFirstLetterMap().get(1), data, "a");
        assertEquals(2, artworks.size());

        artworks = Trie.trieForArtwork(data.getArtworkTitleFirstLetterMap().get(1),
                data, "jkljafd");
        assertEquals(0, artworks.size());
    }

    @Test
    public void trieForAuthor() {
        Data data = new Data();
        data.load("src/data/datasetForTest.csv");
        TreeSet<Author> authorTreeSet = Trie.trieForAuthor("", data);
        assertEquals(4, authorTreeSet.size());

        authorTreeSet = Trie.trieForAuthor("A", data);
        assertEquals(1, authorTreeSet.size());

        authorTreeSet = Trie.trieForAuthor("n", data);
        assertEquals(2, authorTreeSet.size());
    }
}
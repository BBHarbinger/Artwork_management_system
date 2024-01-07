package src.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This is interface for Data class.
 * The data class will load files and
 * keep relevant information (stored in maps and lists).
 */
public class Data {

    // This set is for some optional advanced features
    private static String[] stopWordsSet = { "a", "about", "above", "across",
        "after", "afterwards", "again", "against", "all", "almost", "alone",
        "along", "already", "also", "although", "always", "am", "among",
        "amongst", "amoungst", "amount", "an", "and", "another", "any",
        "anyhow", "anyone", "anything", "anyway", "anywhere", "are", "around",
        "as", "at", "back", "be", "became", "because", "become", "becomes",
        "becoming", "been", "before", "beforehand", "behind", "being", "below",
        "beside", "besides", "between", "beyond", "bill", "both", "bottom",
        "but", "by", "call", "can", "cannot", "cant", "co", "computer", "con",
        "could", "couldnt", "cry", "de", "describe", "detail", "do", "done",
        "down", "due", "during", "each", "eg", "eight", "either", "eleven",
        "else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every",
        "everyone", "everything", "everywhere", "except", "few", "fifteen",
        "fify", "fill", "find", "fire", "first", "five", "for", "former",
        "formerly", "forty", "found", "four", "from", "front", "full",
        "further", "get", "give", "go", "had", "has", "hasnt", "have", "he",
        "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon",
        "hers", "herse", "him", "himse", "his", "how", "however", "hundred",
        "i", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it",
        "its", "itse", "keep", "last", "latter", "latterly", "least", "less",
        "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill",
        "mine", "more", "moreover", "most", "mostly", "move", "much", "must",
        "my", "myse", "name", "namely", "neither", "never", "nevertheless",
        "next", "nine", "no", "nobody", "none", "noone", "nor", "not",
        "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one",
        "only", "onto", "or", "other", "others", "otherwise", "our", "ours",
        "ourselves", "out", "over", "own", "part", "per", "perhaps", "please",
        "put", "rather", "re", "same", "see", "seem", "seemed", "seeming",
        "seems", "serious", "several", "she", "should", "show", "side", "since",
        "sincere", "six", "sixty", "so", "some", "somehow", "someone",
        "something", "sometime", "sometimes", "somewhere", "still", "such",
        "system", "take", "ten", "than", "that", "the", "their", "them",
        "themselves", "then", "thence", "there", "thereafter", "thereby",
        "therefore", "therein", "thereupon", "these", "they", "thick", "thin",
        "third", "this", "those", "though", "three", "through", "throughout",
        "thru", "thus", "to", "together", "too", "top", "toward", "towards",
        "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us",
        "very", "via", "was", "we", "well", "were", "what", "whatever", "when",
        "whence", "whenever", "where", "whereafter", "whereas", "whereby",
        "wherein", "whereupon", "wherever", "whether", "which", "while",
        "whither", "who", "whoever", "whole", "whom", "whose", "why", "will",
        "with", "within", "without", "would", "yet", "you", "your", "yours",
        "yourself", "yourselves", };

    private Set<String> stopWordsSetordSet = new HashSet<>(List.of(stopWordsSet));

    /**
     * When reading from cvs file,
     * each row represents an artwork.
     * An instance will be initiated, and append to
     * the end of the list.
     * So the order of artworks in this list
     * is the same as the order in the CVS file.
     */
    private List<Artwork> artworkList;

    /**
     * This is a map created for customizable selection.
     * The key is an integer to represent the category.
     * "A-I": 1
     * "J-R": 2
     * Other: 3
     *
     * Value is a set of integers. The integer is the row index.
     * In the CVS file, if the artwork's title starts with "b", and it's at row 3
     * "b" belongs to category 1, so the index 3 will be put its set.
     * You may call charToIndex to convert a char to a category.
     */
    private Map<Integer, Set<Integer>> artworkTitleFirstLetterMap;

    /**
     * This is map created for customizable selection.
     * The key is an integer to represent the category.
     * "A-I": 1
     * "J-R": 2
     * Other: 3
     *
     * Value is a set of integers. The integer is the row index.
     * In the CVS file, if the artwork's author starts with "b", and it's at row 3
     * "b" belongs to category 1, so the index 3 will be put its set.
     * You may call charToIndex to convert a char to a category.
     */
    private Map<Integer, Set<Integer>> artworkAuthorFirstLetterMap;

    /**
     * This is a map created for customizable selection.
     * The key is a string to represent genres.
     * The value is a set of integers to represent row index in the CVS file.
     */
    private Map<String, Set<Integer>> genreMap;

    /**
     * A map to map author's name to Author object.
     * Assume author's name does not repeat.
     * Key is in lower case!!!!!!!!!!!!!!!
     */
    private Map<String, Author> authorNameToObject;

    /**
     * A map to map artwork's title to Artwork instances.
     * Value is set, because titles may repeat.
     * Key is in lower case!!!!!!!!!!!!!!!
     */
    private Map<String, Set<Artwork>> artworkTitleToObjectSets;


    /**
     * This is a map.
     * The key is a keyword string.
     * The set is a set of artworks associated with the keyword.
     */
    private Map<String, Set<Artwork>> keywordArtworkMap;

    /**
     * This is a map.
     * The key is a keyword string.
     * The set is a set of authors associated with the keyword.
     */
    private Map<String, Set<Author>> keywordAuthorMap;

    /**
     * This function to be called to load the CVS file.
     * @param filePath a string of the path to the cvs file.
     */
    public void load(String filePath) {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            String[] firstLine = scanner.nextLine().trim().split(","); // skip the first line

            // initialize some variables
            artworkList = new ArrayList<>();
            artworkTitleFirstLetterMap = new HashMap<>();
            artworkAuthorFirstLetterMap = new HashMap<>();
            genreMap = new HashMap<>();
            authorNameToObject = new HashMap<>();
            artworkTitleToObjectSets = new HashMap<>();
            keywordArtworkMap = new HashMap<>();
            keywordAuthorMap = new HashMap<>();

            int i = 0; // to record artwork's index

            while (scanner.hasNext()) {
                // read one line and split
                String[] line = scanner.nextLine().trim().split(",");

                // parse
                String title = line[0].trim();
                String author = line[1].trim();
                String genre = line[2].trim();
                int century = Integer.parseInt(line[3].trim());
                String topic = line[4].trim();
                String medium = line[5].trim();
                String description = line[6].trim();
                String wikiLink = line[7].trim();
                // create a new artwork object
                Artwork artwork = new Artwork(title, author, genre, century, topic,
                        medium, wikiLink, description);
                // add to the artwork list
                artworkList.add(artwork);

                // deal with Map<Integer, Set<Integer>> artworkTitleFirstLetterMap
                Set<Integer> set = artworkTitleFirstLetterMap.
                        getOrDefault(charToIndex(title.charAt(0)), new HashSet<>());
                // add artwork's index to corresponding set
                set.add(i);
                artworkTitleFirstLetterMap.put(charToIndex(title.charAt(0)), set);

                // deal with Map<Integer, Set<Integer>> artworkAuthorFirstLetterMap
                set = artworkAuthorFirstLetterMap.
                        getOrDefault(charToIndex(author.charAt(0)), new HashSet<>());
                set.add(i);
                artworkAuthorFirstLetterMap.put(charToIndex(author.charAt(0)), set);

                // deal with Map<String, Set<Integer>> genreMap
                set = genreMap.getOrDefault(genre, new HashSet<>());
                set.add(i);
                genreMap.put(genre, set);

                Author authorObject;
                // deal with Map<String, Author> authorNameToObject
                if (!authorNameToObject.containsKey(author.toLowerCase())) {
                    // author name does not exist in the map yet
                    // create a new author object
                    authorObject = new Author(author);
                    // add the artwork to author's list
                    TreeSet<Artwork> artworks = authorObject.getArtworks();
                    artworks.add(artwork);
                    authorNameToObject.put(author.toLowerCase(), authorObject);
                } else {
                    // author name already exists in the map
                    // get the author object
                    authorObject = authorNameToObject.get(author.toLowerCase());
                    // add the artwork to author's list
                    TreeSet<Artwork> artworks = authorObject.getArtworks();
                    artworks.add(artwork);
                }


                // deal with Map<String, Set<Artwork>> artworkTitleToObjectSets
                if (!artworkTitleToObjectSets.containsKey(title.toLowerCase())) {
                    // does not contain the artist
                    Set<Artwork> artworkSet = new HashSet<>();
                    artworkSet.add(artwork);
                    artworkTitleToObjectSets.put(title.toLowerCase(), artworkSet);
                } else {
                    // set already exist
                    Set<Artwork> artworkSet = artworkTitleToObjectSets.get(title.toLowerCase());
                    artworkSet.add(artwork);
                    artworkTitleToObjectSets.put(title.toLowerCase(), artworkSet);
                }

                // this deals with some optional advanced features
                String keywordLongString = title + " " + author + " " + genre
                        + " " + topic + " " + medium + " " + description;
                String[] array = keywordLongString.toLowerCase().trim().split(" ");
                for (String str: array) {

                    if (stopWordsSetordSet.contains(str) || str.length() == 1) {
                        continue;
                    }

                    // deal with keywordArtworkMap
                    Set<Artwork> artworkSet = keywordArtworkMap.getOrDefault(str, new HashSet<>());
                    artworkSet.add(artwork);
                    keywordArtworkMap.put(str, artworkSet);

                    // deal with keywordAuthorMap
                    Set<Author> authorSet = keywordAuthorMap.getOrDefault(str, new HashSet<>());
                    authorSet.add(authorObject);
                    keywordAuthorMap.put(str, authorSet);

                }

                // increment i
                i++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This function can convert a given char to
     * its corresponding index (an integer).
     * "A-I": 1
     * "J-R": 2
     * Other: 3
     * @param c
     * @return an integer to represent category.
     */
    public static int charToIndex(char c) {
        if (c >= 'a' && c <= 'z') {
            // check if the character is lowercase or not
            //if it's lowercase, convert it to upper case
            c = Character.toUpperCase(c);
        }

        if (c >= 'A' && c <= 'I') {
            // category 1
            return 1;
        } else if (c >= 'J' && c <= 'R') {
            // category 2
            return 2;
        } else {
            // category 3
            return 3;
        }

    }

    public List<Artwork> getArtworkList() {
        return artworkList;
    }

    public Map<Integer, Set<Integer>> getArtworkTitleFirstLetterMap() {
        return artworkTitleFirstLetterMap;
    }

    public Map<Integer, Set<Integer>> getArtworkAuthorFirstLetterMap() {
        return artworkAuthorFirstLetterMap;
    }

    public Map<String, Set<Integer>> getGenreMap() {
        return genreMap;
    }

    public Map<String, Author> getAuthorNameToObject() {
        return authorNameToObject;
    }

    public Map<String, Set<Artwork>> getArtworkTitleToObjectSets() {
        return artworkTitleToObjectSets;
    }

    public Map<String, Set<Artwork>> getKeywordArtworkMap() {
        return keywordArtworkMap;
    }

    public Map<String, Set<Author>> getKeywordAuthorMap() {
        return keywordAuthorMap;
    }
}
package src.data;

import java.util.TreeSet;

import java.util.*;

/**
 * This class is a recommender system for recommending similar artworks
 * based on various attributes such as author,
 * genre, century, theme, and medium.
 */

public class Recommender {

    /**
     * A nested class that represents an artwork along with its
     * attributes projected onto a high-dimensional space,
     * used for calculating the closest neighbors.
     */
    private class ArtworkForRecommendation {
        Artwork artwork;

        double author;
        double genre;
        int century;
        double theme;
        double medium;
        TreeSet<CloestNeighrbors> closestNeighbors;

        public Artwork getArtwork() {
            return artwork;
        }

        public void setArtwork(Artwork artwork) {
            this.artwork = artwork;
        }

        public double getAuthor() {
            return author;
        }

        public void setAuthor(double author) {
            this.author = author;
        }

        public double getGenre() {
            return genre;
        }

        public void setGenre(double genre) {
            this.genre = genre;
        }

        public int getCentury() {
            return century;
        }

        public void setCentury(int century) {
            this.century = century;
        }

        public double getTheme() {
            return theme;
        }

        public void setTheme(double theme) {
            this.theme = theme;
        }

        public double getMedium() {
            return medium;
        }

        public void setMedium(double medium) {
            this.medium = medium;
        }

        public TreeSet<CloestNeighrbors> getClosestNeighbors() {
            return closestNeighbors;
        }

        public void setClosestNeighbors(TreeSet<CloestNeighrbors> closestNeighbors) {
            this.closestNeighbors = closestNeighbors;
        }
    }


    /**
     * A nested class that represents the closest
     * neighbors of an artwork along with their distances.
     */
    private class CloestNeighrbors implements Comparable<CloestNeighrbors> {

        private Artwork artwork;
        private double distance;

        public Artwork getArtwork() {
            return artwork;
        }

        public void setArtwork(Artwork artwork) {
            this.artwork = artwork;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        @Override
        public int compareTo(CloestNeighrbors o) {
            return -Double.compare(this.distance, o.distance);
        }
    }


    /**
     * Calculates the average century of all authors in the given dataset.
     *
     * @param data The data object containing the artwork list.
     * @return A map with authors as keys and their average century as values.
     */
    public Map<String, Double> getAuthorAverageCenturyMap(Data data) {
        List<Artwork> listofwork = data.getArtworkList();
        // Map to store the sum of centuries for each author
        Map<String, Long> authorCenturySumMap = new HashMap<>();
        // Map to store the count of artworks for each author
        Map<String, Long> authorArtworkCountMap = new HashMap<>();

        // Loop through listofwork to find all authors and their corresponding centuries
        for (Artwork artwork : listofwork) {

            String author = artwork.getAuthor();
            int century = artwork.getCentury();

            // Update authorCenturySumMap and authorArtworkCountMap for the current artwork
            authorCenturySumMap.put(author, authorCenturySumMap.getOrDefault(author, 0L) + century);
            authorArtworkCountMap.put(author, authorArtworkCountMap.getOrDefault(author, 0L) + 1);

        }

        // Calculate the average century of all authors
        Map<String, Double> authorAverageCenturyMap = new HashMap<>();
        for (Map.Entry<String, Long> entry : authorCenturySumMap.entrySet()) {

            String author = entry.getKey();
            long centurySum = entry.getValue();
            long artworkCount = authorArtworkCountMap.get(author);
            double averageCentury = (double) centurySum / artworkCount;
            authorAverageCenturyMap.put(author, averageCentury);

        }

        return authorAverageCenturyMap;
    }


    /**
     * Calculates the average century of all genres in the given dataset.
     *
     * @param data The data object containing the artwork list.
     * @return A map with genres as keys and their average century as values.
     */
    public Map<String, Double> getGenreAverageCenturyMap(Data data) {
        List<Artwork> listofwork = data.getArtworkList();

        // Map to store the sum of centuries for each genre
        Map<String, Long> genreCenturySumMap = new HashMap<>();
        // Map to store the count of artworks for each genre
        Map<String, Long> genreArtworkCountMap = new HashMap<>();

        // Loop through listofwork to find all genres and their corresponding centuries
        for (Artwork artwork : listofwork) {

            String genre = artwork.getGenre();
            int century = artwork.getCentury();

            // Update genreCenturySumMap and genreArtworkCountMap for the current artwork
            genreCenturySumMap.put(genre, genreCenturySumMap.getOrDefault(genre, 0L) + century);
            genreArtworkCountMap.put(genre, genreArtworkCountMap.getOrDefault(genre, 0L) + 1);

        }

        // Calculate the average century of all genres
        Map<String, Double> genreAverageCenturyMap = new HashMap<>();
        for (Map.Entry<String, Long> entry : genreCenturySumMap.entrySet()) {

            String genre = entry.getKey();
            long centurySum = entry.getValue();
            long artworkCount = genreArtworkCountMap.get(genre);
            double averageCentury = (double) centurySum / artworkCount;
            genreAverageCenturyMap.put(genre, averageCentury);

        }

        return genreAverageCenturyMap;
    }


    /**
     * Calculates the average century of all themes in the given dataset.
     *
     * @param data The data object containing the artwork list.
     * @return A map with themes as keys and their average century as values.
     */

    public Map<String, Double> getThemeAverageCenturyMap(Data data) {
        List<Artwork> listofwork = data.getArtworkList();

        // Map to store the sum of centuries for each theme
        Map<String, Long> themeCenturySumMap = new HashMap<>();
        // Map to store the count of artworks for each theme
        Map<String, Long> themeArtworkCountMap = new HashMap<>();

        // Loop through listofwork to find all themes and their corresponding centuries
        for (Artwork artwork : listofwork) {
            String theme = artwork.getTopic();
            int century = artwork.getCentury();

            // Update themeCenturySumMap and themeArtworkCountMap for the current artwork
            themeCenturySumMap.put(theme, themeCenturySumMap.getOrDefault(theme, 0L) + century);
            themeArtworkCountMap.put(theme, themeArtworkCountMap.getOrDefault(theme, 0L) + 1);
        }

        // Calculate the average century of all themes
        Map<String, Double> themeAverageCenturyMap = new HashMap<>();
        for (Map.Entry<String, Long> entry : themeCenturySumMap.entrySet()) {
            String theme = entry.getKey();
            long centurySum = entry.getValue();
            long artworkCount = themeArtworkCountMap.get(theme);
            double averageCentury = (double) centurySum / artworkCount;
            themeAverageCenturyMap.put(theme, averageCentury);
        }

        return themeAverageCenturyMap;
    }


    /**
     * Calculates the average century of all mediums in the given dataset.
     *
     * @param data The data object containing the artwork list.
     * @return A map with mediums as keys and their average century as values.
     */
    public Map<String, Double> getMediumAverageCenturyMap(Data data) {
        List<Artwork> listofwork = data.getArtworkList();

        // Map to store the sum of centuries for each medium
        Map<String, Long> mediumCenturySumMap = new HashMap<>();
        // Map to store the count of artworks for each medium
        Map<String, Long> mediumArtworkCountMap = new HashMap<>();

        // Loop through listofwork to find all mediums and their corresponding centuries
        for (Artwork artwork : listofwork) {
            String medium = artwork.getMedium();
            int century = artwork.getCentury();

            // Update mediumCenturySumMap and mediumArtworkCountMap for the current artwork
            mediumCenturySumMap.put(medium, mediumCenturySumMap.getOrDefault(medium, 0L) + century);
            mediumArtworkCountMap.put(medium, mediumArtworkCountMap.getOrDefault(medium, 0L) + 1);
        }

        // Calculate the average century of all mediums
        Map<String, Double> mediumAverageCenturyMap = new HashMap<>();
        for (Map.Entry<String, Long> entry : mediumCenturySumMap.entrySet()) {
            String medium = entry.getKey();
            long centurySum = entry.getValue();
            long artworkCount = mediumArtworkCountMap.get(medium);
            double averageCentury = (double) centurySum / artworkCount;
            mediumAverageCenturyMap.put(medium, averageCentury);
        }

        return mediumAverageCenturyMap;
    }


    /**
     * This method should be called after load the cvs file.
     * This method will project entire artworks onto a high dimension space.
     * And calculate each neighbor's closest neighbors.
     * Closest neighbors should share similarities, and can be recommended.
     *
     * @param data object
     * @param k a integer to calculate how many closest neighbors should be stored.
     */
    public void build(Data data, int k) {
        List<ArtworkForRecommendation> artworkForRecommendationList = new ArrayList<>();
        List<Artwork> listofwork = data.getArtworkList();
        Map<String, Double> authorNum = this.getAuthorAverageCenturyMap(data);
        Map<String, Double> genreNum = this.getGenreAverageCenturyMap(data);
        Map<String, Double> themeNum = this.getThemeAverageCenturyMap(data);
        Map<String, Double> mediumNum = this.getMediumAverageCenturyMap(data);

        // Loop through listofwork to create ArtworkForRecommendation objects
        for (Artwork artwork : listofwork) {
            ArtworkForRecommendation recommendation = new ArtworkForRecommendation();
            recommendation.setArtwork(artwork);
            recommendation.setCentury(artwork.getCentury());
            String author = artwork.getAuthor();
            if (authorNum.containsKey(author)) {
                double authorAvgCentury = authorNum.get(author);
                recommendation.setAuthor(authorAvgCentury);
            }
            String genre = artwork.getGenre();
            if (genreNum.containsKey(genre)) {
                double genreAvgCentury = genreNum.get(genre);
                recommendation.setGenre(genreAvgCentury);
            }
            String theme = artwork.getTopic();
            if (themeNum.containsKey(theme)) {
                double themeAvgCentury = themeNum.get(theme);
                recommendation.setTheme(themeAvgCentury);
            }
            String medium = artwork.getMedium();
            if (mediumNum.containsKey(medium)) {
                double mediumAvgCentury = mediumNum.get(medium);
                recommendation.setMedium(mediumAvgCentury);
            }
            artworkForRecommendationList.add(recommendation);
        }


        // Loop through artworkForRecommendationList to find the closest neighbors for each artwork
        for (ArtworkForRecommendation artwork : artworkForRecommendationList) {
            TreeSet<CloestNeighrbors> closestNeighbors = new TreeSet<>();
            for (ArtworkForRecommendation other : artworkForRecommendationList) {
                if (!artwork.equals(other)) {
                    double distance = Math.pow(artwork.getAuthor() - other.getAuthor(), 2)
                            + Math.pow(artwork.getGenre() - other.getGenre(), 2)
                            + Math.pow(artwork.getCentury() - other.getCentury(), 2)
                            + Math.pow(artwork.getTheme() - other.getTheme(), 2)
                            + Math.pow(artwork.getMedium() - other.getMedium(), 2);
                    CloestNeighrbors neighbor = new CloestNeighrbors();
                    neighbor.setArtwork(other.getArtwork());
                    neighbor.setDistance(distance);
                    closestNeighbors.add(neighbor);
                    if (closestNeighbors.size() > k) {
                        closestNeighbors.pollFirst();
                    }
                }
            }
            artwork.setClosestNeighbors(closestNeighbors);
        }

        for (ArtworkForRecommendation i : artworkForRecommendationList) {
            List<Artwork> list = new ArrayList<>();
            for (CloestNeighrbors j : i.getClosestNeighbors()) {
                list.add(j.getArtwork());
            }
            i.getArtwork().setClosestNeighbors(list);
        }
    }
}
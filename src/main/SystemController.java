package src.main;

import src.data.Artwork;
import src.data.Author;
import src.data.Data;
import src.data.Recommender;

import java.io.*;
import java.util.*;

/**
 * The Main Class that controls the system
 */
public class SystemController {

    /*
     * The global variables
     */
    private Data data;

    public static void main(String[] args) {
        SystemController control = new SystemController();
        control.run();
    }

    /**
     * run method controls the system entirely
     */
    public void run() {
        this.data = new Data();
        //load the data from the dataset.csv file in the src/data folder
        String filepath = "src/data/dataset.csv";
        //load the data from the dataset.csv file in the src/data folder
        data.load(filepath);
        Recommender rec = new Recommender();
        rec.build(data, 5);

        System.out.println("\n==========================\n" +
                           "WELCOME TO PENN ART MUSEUM!\n" +
                           "==========================\n" +
                           "Please use full screen (for terminal) for the best experience.\n");

        pressEnterToContinue();
        welcomeInfo();
        // Create a Scanner object
        Scanner scan = new Scanner(System.in);

        //Continue running the system until the user quits
        while (true) {
            int mainMenuResult = mainMenu(scan);
            /*
             * Branch 1
             */

            if (mainMenuResult == 1) {
                while (true) {
                    //Select by the name of the painting
                    System.out.println("==========================================\n" +
                            "--> [Search Art Works by Name]\n" +
                            "==========================================");
                    int input1 = alphabetPartSelection(scan);
                    //quit if input is 0
                    if (input1 == 0) {
                        pressEnterToContinue();
                        break;
                    }
                    //Get the related set of artwork titles
                    String start;
                    String end;
                    Set<Integer> artworkTitleSet;
                    if (input1 == 1) {
                        // "A-I": 1
                        start = "A";
                        end = "I";
                        artworkTitleSet = data.getArtworkTitleFirstLetterMap().get(1);
                    } else if (input1 == 2) {
                        // "J-R": 2
                        start = "J";
                        end = "R";
                        artworkTitleSet = data.getArtworkTitleFirstLetterMap().get(2);
                    } else {
                        // "S-Z": 3
                        start = "S";
                        end = "Z";
                        artworkTitleSet = data.getArtworkTitleFirstLetterMap().get(3);
                    }

                    //Use autocomplete to find the artwork
                    Artwork artwork = AutoCompleteUserInterFace.autoCompleteArtwork(
                            artworkTitleSet, this.data, scan, start, end);
                    if (artwork == null) {
                        continue;
                    //show the information of this artwork
                    } else {
                        System.out.println("\nYou have selected: \n[" + artwork.getTitle() +
                                " -- " + artwork.getAuthor() + "]");
                        printPic(artwork.getTitle(), artwork.getAuthor());
                        System.out.println("==========================================\n" +
                                "--> [Artwork Information]\n" +
                                "==========================================");
                        System.out.println("[Title]: " + artwork.getTitle());
                        System.out.println("[Author]: " + artwork.getAuthor());
                        System.out.println("[Genre]: " + artwork.getGenre());
                        System.out.println("[Century]: " + artwork.getCentury() + "th");
                        System.out.println("[Topic]: " + artwork.getTopic());
                        System.out.println("[Medium]: " + artwork.getMedium());
                        System.out.println("[Short Description]: " + artwork.getShortDescription());
                        System.out.println("[Wiki Link]: " + artwork.getWikiLink());
                        System.out.println("==========================================");

                        //Ask the user if he wants similar artworks
                        int input2 = optionSelection("Do you want to see similar " +
                                        "artworks? Press 1 for yes.",
                                "quiting to the previous menu...\n", scan, 1, 1);
                        if (input2 == 0) {
                            continue;
                        } else {
                            while (true) {
                                artwork = selectNeighbour(artwork, scan);
                                int input3 = optionSelection("Do you want to see similar " +
                                                "artworks? Press 1 for yes.",
                                        "quiting to the main menu...\n", scan, 1, 1);
                                if (input3 == 0) {
                                    break;
                                }
                            }
                            pressEnterToContinue();
                            break;
                        }


                    }

                }


            /*
             * Branch 2
             */
            } else if (mainMenuResult == 2) {
                //Select by the name of the artist
                while (true) {
                    System.out.println();
                    int input1 = optionSelection("\n==========================================\n" +
                                                        "--> [Search Art Works by Artist]\n" +
                                                        "==========================" +
                                    "================\n" +
                            "Press 1 to start searching", "quiting to the main menu...\n"
                            , scan, 1, 1);
                    if (input1 == 0) {
                        break;
                    }
                    Author author = AutoCompleteUserInterFace.autoCompleteArtist(this.data, scan);
                    if (author == null) {
                        continue;
                        //show the information of this artwork
                    }
                    System.out.println("\nYou have selected: \n[" + author.getName() + "]");
                    System.out.println("==========================================\n" +
                            "--> " + author.getName() + "'s Artworks");
                    TreeSet<Artwork> artworks = author.getArtworks();
                    List<Artwork> artworksNames = new ArrayList<>();
                    for (Artwork artwork : artworks) {
                        artworksNames.add(artwork);
                    }
                    int count = 0;
                    while (true) {
                        count = 0;
                        System.out.println("==========================================");
                        for (Artwork artwork : artworks) {
                            System.out.println((count + 1) + ": [" + artwork.getTitle() + "]");
                            count++;
                        }
                        System.out.println("==========================================\n");
                        int input2 = optionSelection("Please select an artist by index",
                                "quiting to the previous menu...\n", scan, 1, count);
                        if (input2 == 0) {
                            break;
                        } else {
                            Artwork selected = artworksNames.get(input2 - 1);
                            System.out.println("\nYou have selected: \n[" +
                                    selected.getTitle() + " -- " +
                                    selected.getAuthor() + "]");
                            printPic(selected.getTitle(), selected.getAuthor());
                            System.out.println("==========================================\n" +
                                    "--> [Artwork Information]\n" +
                                    "==========================================");
                            System.out.println("[Title]: " + selected.getTitle());
                            System.out.println("[Author]: " + selected.getAuthor());
                            System.out.println("[Genre]: " + selected.getGenre());
                            System.out.println("[Century]: " + selected.getCentury() + "th");
                            System.out.println("[Topic]: " + selected.getTopic());
                            System.out.println("[Medium]: " +
                                    selected.getMedium());
                            System.out.println("[Short Description]: " +
                                    selected.getShortDescription());
                            System.out.println("[Wiki Link]: " + selected.getWikiLink());
                            System.out.println("==========================================");

                            //Ask the user if he wants similar artworks
                            int input3 = optionSelection("Do you want to see similar " +
                                            "artworks? Press 1 for yes.",
                                    "quiting to the previous menu...\n", scan, 1, 1);
                            if (input3 == 0) {
                                continue;
                            } else {
                                while (true) {
                                    selected = selectNeighbour(selected, scan);
                                    int input4 = optionSelection("Do you want to see " +
                                                    "similar artworks? Press 1 for yes.",
                                            "quiting to the previous menu...\n", scan, 1, 1);
                                    if (input4 == 0) {
                                        break;
                                    }
                                }
                                pressEnterToContinue();
                                break;
                            }
                        }

                    }

                    int input2 = optionSelection("Press 1 to search for another " +
                            "artist.", "quiting to the main menu...\n",
                            scan, 1, 1);
                    if (input2 == 0) {
                        pressEnterToContinue();
                        break;
                    }
                }


            /*
             * Branch 3
             */
            } else if (mainMenuResult == 3) {
                int count = 0;
                while (true) {
                    //Select by genre
                    System.out.println("==========================================\n" +
                            "--> [Search Art Works by Genre]\n");
                    Map<String, Set<Integer>> genreMap = this.data.getGenreMap();
                    List<String> genreList = new ArrayList<>();
                    List<Set<Integer>> genreSets = new ArrayList<>();
                    //turn the map into lists
                    for (String genre : genreMap.keySet()) {
                        genreList.add(genre);
                        genreSets.add(genreMap.get(genre));
                    }

                    count = 0;
                    System.out.println("==========================================");
                    for (String genre : genreList) {
                        System.out.println((count + 1) + ": [" + genreList.get(count) + "]");
                        count++;
                    }
                    System.out.println("==========================================\n");
                    int input1 = optionSelection("Please select a genre by index",
                            "quiting to the main menu...\n", scan, 1, count);
                    if (input1 == 0) {
                        break;
                    }
                    Set<Integer> artworkSetG = genreSets.get(input1 - 1);
                    int input2 = optionSelection("Press 1 to narrow down the " +
                            "selection by author name.\n" +
                            "Press 2 to show the result", "quiting to the " +
                            "previous menu...\n", scan, 1, 2);
                    if (input2 == 0) {
                        break;

                    //Just show the list
                    } else if (input2 == 2) {
                        //Get the list of the artworks
                        List<Artwork> artworks = new ArrayList<>();
                        for (Integer index : artworkSetG) {
                            artworks.add(this.data.getArtworkList().get(index));
                        }
                        while (true) {
                            count = 0;
                            System.out.println("==========================================");
                            for (Artwork artwork : artworks) {
                                System.out.println((count + 1) + ": [" + artwork.getTitle()
                                        + "]  --  " +
                                        artwork.getAuthor());
                                count++;
                            }
                            System.out.println("==========================================\n");
                            int input3 = optionSelection("Please select an artwork by index",
                                    "quiting to the previous menu...\n", scan, 1, count);
                            if (input3 == 0) {
                                break;
                            } else {
                                Artwork selected = artworks.get(input3 - 1);
                                System.out.println("\nYou have selected: \n[" +
                                        selected.getTitle() + " -- " +
                                        selected.getAuthor() + "]");
                                printPic(selected.getTitle(), selected.getAuthor());
                                System.out.println("==========================================\n" +
                                        "--> [Artwork Information]\n" +
                                        "==========================================");
                                System.out.println("[Title]: " + selected.getTitle());
                                System.out.println("[Author]: " + selected.getAuthor());
                                System.out.println("[Genre]: " + selected.getGenre());
                                System.out.println("[Century]: " + selected.getCentury() + "th");
                                System.out.println("[Topic]: " + selected.getTopic());
                                System.out.println("[Medium]: " +
                                        selected.getMedium());
                                System.out.println("[Short Description]: " +
                                        selected.getShortDescription());
                                System.out.println("[Wiki Link]: " + selected.getWikiLink());
                                System.out.println("==========================================");

                                //Ask the user if he wants similar artworks
                                input3 = optionSelection("Do you want to see similar " +
                                                "artworks? Press 1 for yes.",
                                        "quiting to the previous menu...\n", scan, 1, 1);
                                if (input3 == 0) {
                                    continue;
                                } else {
                                    while (true) {
                                        selected = selectNeighbour(selected, scan);
                                        int input4 = optionSelection("Do you want to see " +
                                                        "similar artworks? Press 1 for yes.",
                                                "quiting to the previous menu...\n", scan, 1, 1);
                                        if (input4 == 0) {
                                            break;
                                        }
                                    }
                                    pressEnterToContinue();
                                    break;
                                }
                            }
                        }

                        //Narrow down the selection by author name
                    } else {
                        int input3 = optionSelection("Please enter the range of author name\n" +
                                        "1. [A - I]\n" +
                                        "2. [J - R]\n" +
                                        "3. [S - Z]\n",
                                "quiting to the previous menu...\n", scan, 1, 3);
                        Set<Integer> artworkSetA;
                        if (input3 == 1) {
                            artworkSetA = this.data.getArtworkAuthorFirstLetterMap().get(1);
                        } else if (input3 == 2) {
                            artworkSetA = this.data.getArtworkAuthorFirstLetterMap().get(2);
                        } else {
                            artworkSetA = this.data.getArtworkAuthorFirstLetterMap().get(3);
                        }
                        //find the intersection of the two sets
                        Set<Integer> intersection = new HashSet<>(artworkSetG);
                        intersection.retainAll(artworkSetA);

                        //Get the list of the artworks
                        List<Artwork> artworks = new ArrayList<>();
                        for (Integer index : intersection) {
                            artworks.add(this.data.getArtworkList().get(index));
                        }
                        if (artworks.size() == 0) {
                            System.out.println("No matched artwork found.");
                            pressEnterToContinue();
                            continue;
                        }

                        //Ask the user to select an artwork
                        while (true) {
                            count = 0;
                            System.out.println("==========================================");
                            for (Artwork artwork : artworks) {
                                System.out.println((count + 1) + ": [" + artwork.getTitle() + "]");
                                count++;
                            }
                            System.out.println("==========================================\n");
                            int input4 = optionSelection("Please select an artwork by index",
                                    "quiting to the previous menu...\n", scan, 1, count);
                            if (input4 == 0) {
                                break;
                            } else {
                                Artwork selected = artworks.get(input4 - 1);
                                System.out.println("\nYou have selected: \n[" +
                                        selected.getTitle() + " -- " +
                                        selected.getAuthor() + "]");
                                printPic(selected.getTitle(), selected.getAuthor());
                                System.out.println("==========================================\n" +
                                        "--> [Artwork Information]\n" +
                                        "==========================================");
                                System.out.println("[Title]: " + selected.getTitle());
                                System.out.println("[Author]: " + selected.getAuthor());
                                System.out.println("[Genre]: " + selected.getGenre());
                                System.out.println("[Century]: " + selected.getCentury() + "th");
                                System.out.println("[Topic]: " + selected.getTopic());
                                System.out.println("[Medium]: " + selected.getMedium());
                                System.out.println("[Short Description]: " +
                                        selected.getShortDescription());
                                System.out.println("[Wiki Link]: " + selected.getWikiLink());
                                System.out.println("==========================================");

                                //Ask the user if he wants similar artworks
                                input3 = optionSelection("Do you want to see similar " +
                                                "artworks? Press 1 for yes.",
                                        "quiting to the previous menu...\n", scan, 1, 1);
                                if (input3 == 0) {
                                    continue;
                                } else {
                                    while (true) {
                                        selected = selectNeighbour(selected, scan);
                                        input4 = optionSelection("Do you want to see " +
                                                        "similar artworks? Press 1 for yes.",
                                                "quiting to the previous menu...\n", scan, 1, 1);
                                        if (input4 == 0) {
                                            break;
                                        }
                                    }
                                    pressEnterToContinue();
                                    break;
                                }
                            }





                        }


                    }

                }
            }
        }
    }


    /*
     * Helper functions
     */
    /**
     * Showing the menu options
     * @param options the list of menu options
     *                e.g. {"Option 1", "Option 2", "Option 3"}
     *                that will show
     */
    public void showMenuOptions(List<String> options, String optionalInfo) {
        //if there is optional info, print it
        if (!optionalInfo.equals("")) {
            System.out.println(optionalInfo);
        }
        //print the menu options
        for (int i = 0; i < options.size(); i++) {
            System.out.println(i + 1 + " -- " + options.get(i));
        }
        System.out.println("------------------------------------------");
    }

    /**
     * Get the user input for selecting menu options
     * @param prompt the prompt message
     * @param scanner the scanner
     * @param start smallest integer option
     * @param end largest integer option
     * @param quitInfo the quit info
     * @return the user input
     */
    public int optionSelection(String prompt, String quitInfo,
                               Scanner scanner, int start, int end) {
        //Prompt the user to enter a number
        System.out.println(prompt);
        //Ask for input or esc to quit
        System.out.println("Please enter a number between " + start + " - " + end
                + "\nOr enter 0 to quit");
        int input = 0;
        boolean repeat = true;
        while (repeat) {
            try {
                repeat = false;
                scanner.reset();
                input = scanner.nextInt();
                //Quit if the user input is 0
                if (input == 0) {
                    System.out.println(quitInfo);
                    return 0;
                    //Prompts again if the number is not within range
                } else if (input < start || input > end) {
                    System.out.printf("Please enter an valid number.\n" +
                            "The number should be between %d - %d.\n", start, end);
                    repeat = true;

                }

            //Prompts again if the user input is not a valid integer,
            } catch (Exception e) {
                scanner.next();
                repeat = true;
                System.out.println("Please enter an valid number.");
            }
        }
        return input;
    }

    /**
     * Ask the user to press enter to continue
     */
    public void pressEnterToContinue() {
        System.out.println("Press enter to continue...");
        try {
            //Wait for the user to press enter
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Main menu
     * @param scan the scanner
     * @return the user input
     */
    public int mainMenu(Scanner scan) {
        //Main menu options
        //0. Quit
        //1. Select by the name of the painting
        //2. Select by the name of the artist
        //3. Select by genre
        List<String> mainMenuOptions = new ArrayList<>();
        String main1 = "Search Artworks by Name";
        String main2 = "Search Artworks by Artist";
        String main3 = "Search Artworks by genre";
        //Add the options to the list
        mainMenuOptions.add(main1);
        mainMenuOptions.add(main2);
        mainMenuOptions.add(main3);
        //Main menu info
        String mainMenuInfo =
                "==========================================\n" +
                "--> [MAIN MENU]\n" +
                "==========================================";
        showMenuOptions(mainMenuOptions, mainMenuInfo);
        //Get the user input
        int mainMenuInput = optionSelection("Please enter your option:", "", scan, 0, 3);
        if (mainMenuInput == 0) {
            //Quit
            exitSystem();
        }
        //confirm the user input
        System.out.println("You have selected: [" + mainMenuOptions.get(mainMenuInput - 1) + "]\n" +
                "Loading...\n");
        return mainMenuInput;
    }


    public int alphabetPartSelection(Scanner scan) {
        //Main menu options
        //0. Quit
        //1. [A ~ J]
        //2. [K ~ R]
        //3. [S ~ Z]
        List<String> alphabet = new ArrayList<>();
        String main1 = "[A ~ J]";
        String main2 = "[K ~ R]";
        String main3 = "[S ~ Z]";
        //Add the options to the list
        alphabet.add(main1);
        alphabet.add(main2);
        alphabet.add(main3);
        showMenuOptions(alphabet, "Please select the searching range:");
        int menuInput = optionSelection("", "Quiting to main menu...", scan, 0, 3);
        if (menuInput == 0) {
            return 0;
        } else {
            //confirm the user input
            System.out.println("You have selected: [" + alphabet.get(menuInput - 1) + "]\n" +
                    "Loading...\n");
            return menuInput;
        }
    }


    /**
     * Welcome info
     */
    public void welcomeInfo() {
        Scanner scan = new Scanner(System.in);
        // Greetings from CCP
        String[] lines = Pictures.getMonaLisa().split("\n");
        for (String line : lines) {
            System.out.println(line);
            try {
                Thread.sleep(30); // add a 500 millisecond pause between each print statement
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(xjpIcon);
        List<String> welcome = new ArrayList<>();
        welcome.add("Yes!");
        welcome.add("Absolutely!");
        System.out.println("\nAre you ready for the journey?\n" +
                "------------------------------------------");
        showMenuOptions(welcome, "");
        int enterSystem = optionSelection("", "", scan, 1, 2);
        if (enterSystem == 0) {
            exitSystem();
        }
        String[] lines2 = Pictures.getCafeTerrace().split("\n");
        for (String line : lines2) {
            System.out.println(line);
            try {
                Thread.sleep(30); // add a 500 millisecond pause between each print statement
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(
                        "Fantastic.\nLoading the arts...\n" +
                "========================================\n");
        pressEnterToContinue();
    }

    /**
     * Exit the system.
     */
    public void exitSystem() {
        String[] lines = Pictures.getCafeTerrace().split("\n");
        for (String line : lines) {
            System.out.println(line);
            try {
                Thread.sleep(30); // add a 500 millisecond pause between each print statement
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hope you enjoyed the art!\n" +
                "========================================\n" +
                "Quitting the system...See you again!");
        //quit the system
        System.exit(0);
    }


    public void showTheArtworkList(int input, Set<Artwork> artworks,
                                   List<Artwork> artworksNames, Scanner scan) {
        int count = 0;
        while (true) {
            count = 0;
            System.out.println("==========================================");
            for (Artwork artwork : artworks) {
                System.out.println((count + 1) + ": [" + artwork.getTitle()
                        + "] -- " + artwork.getAuthor());
                count++;
            }
            System.out.println("==========================================\n");
            int input2 = optionSelection("Please select an artwork by index",
                    "quiting to the previous menu...\n", scan, 1, count);
            if (input2 == 0) {
                break;
            } else {
                Artwork selected = artworksNames.get(input2 - 1);
                System.out.println("\nYou have selected: \n[" + selected.getTitle() + " -- " +
                        selected.getAuthor() + "]");
                System.out.println("==========================================\n" +
                        "--> [Artwork Information]\n" +
                        "==========================================");
                System.out.println("[Title]: " + selected.getTitle());
                System.out.println("[Author]: " + selected.getAuthor());
                System.out.println("[Genre]: " + selected.getGenre());
                System.out.println("[Century]: " + selected.getCentury() + "th");
                System.out.println("[Topic]: " + selected.getTopic());
                System.out.println("[Medium]: " + selected.getMedium());
                System.out.println("[Short Description]: " + selected.getShortDescription());
                System.out.println("[Wiki Link]: " + selected.getWikiLink());
                System.out.println("==========================================");

                input2 = optionSelection("Press 1 to select another artwork of the same artist.",
                        "quiting to the previous menu...\n", scan, 1, 1);
                if (input2 == 0) {
                    break;
                }
            }

        }
    }

    public Artwork selectNeighbour(Artwork art, Scanner scan) {
        List<Artwork> artworks = art.getClosestNeighbors();
        //if there is no neighbour, return null
        if (artworks.size() == 0) {
            System.out.println("No similar artwork found.\n" + "" +
                    "This one is unique!");
            return null;
        }
        int count = 0;
        while (true) {
            count = 0;
            System.out.println("==========================================");
            for (Artwork artwork : artworks) {
                System.out.println((count + 1) + ": [" + artwork.getTitle()
                        + "] -- " + artwork.getAuthor());
                count++;
            }
            System.out.println("==========================================\n");
            int input2 = optionSelection("Please select an artwork by index",
                    "quiting to the previous menu...\n", scan, 1, count);
            if (input2 == 0) {
                break;
            } else {
                Artwork selected = artworks.get(input2 - 1);
                System.out.println("\nYou have selected: \n[" + selected.getTitle() + " -- " +
                        selected.getAuthor() + "]");
                printPic(selected.getTitle(), selected.getAuthor());
                System.out.println("==========================================\n" +
                        "--> [Artwork Information]\n" +
                        "==========================================");
                System.out.println("[Title]: " + selected.getTitle());
                System.out.println("[Author]: " + selected.getAuthor());
                System.out.println("[Genre]: " + selected.getGenre());
                System.out.println("[Century]: " + selected.getCentury() + "th");
                System.out.println("[Topic]: " + selected.getTopic());
                System.out.println("[Medium]: " + selected.getMedium());
                System.out.println("[Short Description]: " + selected.getShortDescription());
                System.out.println("[Wiki Link]: " + selected.getWikiLink());
                System.out.println("==========================================");
                return selected;
            }
        }
        return null;
    }

    /**
     * Search for the txt file in the pic folder by name and print it
     * @param name The name of the artwork
     * @param author The author of the artwork
     */
    public void printPic(String name, String author) {
        String filePath = "src/pic/" + name + "_" + author + ".txt";
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Couldn't find: " + filePath);
            return;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}







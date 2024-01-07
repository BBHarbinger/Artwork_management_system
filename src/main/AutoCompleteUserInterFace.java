package src.main;

import src.data.Artwork;
import src.data.Author;
import src.data.Data;
import src.trie.Trie;

import java.util.*;

/**
 * This class is the user interface for the AutoComplete class.
 * Directly called by SystemController.
 */

public class AutoCompleteUserInterFace {
    /*
     * Local variables
     */

    /**
     * This method takes a word from the user input.
     * @param sc the scanner
     * @return the word
     */
    public static String takeAWord(Scanner sc, String type) {
        boolean repeat = true;

        while (repeat) {
            try {
                String input = "";
                repeat = false;
                sc.reset();
                input += sc.nextLine();
                //Quit if the user input is "-", which means backspace
                //Return "0"
                if (input.equals("-")) {
                    return "backspace";
                } else if (input.equals("select")) {
                    return "select";
                } else if (input.equals("quit")) {
                    return "quit";
                } else if (input.equals("\s")) {
                    return " ";
                }
                //if the user input length is not 1, or not in alphabet, space, or ', prompt again
                if (input.length() != 1 || !input.matches(
                        "[a-zA-Z' ]+")) {
                    repeat = true;
                    System.out.println("Please enter an alphabet letter, " +
                            "space, or apostrophes." +
                            "\nEnter \"select\" to select the " + type + ", " +
                            "or \"quit\" to quit to previous menu.");
                //check if the input is in the range
                }  else {
                    //if the user input is valid, return the input
                    return input;
                }

            }
            //catch exception if fail to read input
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * This method is to auto complete the artwork name.
     * @param artworkSet
     * @param data
     * @param scan
     * @return
     */
    public static Artwork autoCompleteArtwork(Set<Integer> artworkSet,
                                              Data data, Scanner scan,
                                              String start, String end) {
        char startU = start.toUpperCase().charAt(0);
        char endU = end.toUpperCase().charAt(0);
        char startL = start.toLowerCase().charAt(0);
        char endL = end.toLowerCase().charAt(0);
        //Ask the user to input the prefix, one letter at a time
        List<String> artworksNames = new ArrayList<>();
        Set<Artwork> artworks = new TreeSet<>();
        int count = 0;
        String prefix = "";
        boolean repeat = true;
        while (repeat) {
            scan.reset();
            String character = takeAWord(scan, "artwork");
            //if quit is entered, quit to previous menu
            if (character.equals("quit")) {
                break;
            }
            if (character.equals("backspace")) {
                if (prefix.length() > 0) {
                    prefix = prefix.substring(0, prefix.length()
                            - 1);
                    if (prefix.length() == 0) {
                        System.out.println("-->");
                        continue;
                    }
                }
            } else if (character.equals("select")) {
                if (prefix.length() == 0) {
                    System.out.println("Please enter at least " +
                            "one letter.");
                    continue;
                } else if (artworks.size() == 0) {
                    System.out.println("No match found, going back " +
                            "to searching.");
                    System.out.println("--> " + prefix);
                    continue;
                }
                System.out.println("Please select an artwork by index, " +
                        "or enter 0 to continue searching.\n" +
                        "Enter -1 to quit to previous menu.");
                //scan the next integer
                scan.reset();
                int index = scan.nextInt();
                //make sure the index is valid
                while (index < -1 || index - 1 > count) {
                    System.out.println("Please enter a valid index.");
                    index = scan.nextInt();
                }
                //if the index is -1, quit to previous menu
                if (index == -1) {
                    repeat = false;
                }
                //if the index is 0, continue searching
                else if (index == 0) {
                    System.out.println("--> " + prefix);
                    continue;
                } else {
                    //if the index is valid, get the artwork from the set
                    for (Artwork art : artworks) {
                        if (art.getTitle().equals(artworksNames.
                                get(index - 1))) {
                            return art;
                        }
                    }
                }


            } else {
                prefix += character;
                if (prefix.length() == 1 && (!((prefix.charAt(0)
                        >= startU && prefix.charAt(0) <= endU) ||
                        (prefix.charAt(0) >= startL && prefix.charAt(0)
                                <= endL)))) {
                    System.out.println("Please enter an alphabet letter " +
                            "between " + startU + " and " + endU + "." +
                            "\n Or enter \"stop\" to stop the input.");
                    //reset the prefix
                    prefix = "";
                    continue;
                }

            }
            //Get the set of artwork names that match the prefix
            artworks = Trie.trieForArtwork(artworkSet, data, prefix);
            System.out.println("-------------------------------" +
                    "------------");
            System.out.println("Artwork names that match the " +
                    "prefix: " + prefix +
                    "\nEnter \"select\" to select artwork, \nor " +
                    "\"quit\" to quit to previous menu");
            System.out.println("-------------------------------" +
                    "------------");
            artworksNames = new ArrayList<>();
            //If there is no match, print "No match found"
            if (artworks.size() == 0) {
                System.out.println("No match found");
            }
            //Take the first 9 matches and print them out
            else {
                count = 0;
                for (Artwork artwork : artworks) {
                    if (count < 9) {
                        System.out.println((count+1) + ": [" +
                                artwork.getTitle() + "] -- " +
                                artwork.getAuthor());
                        count++;
                    }
                    artworksNames.add(artwork.getTitle());
                }
            }
            System.out.println("-------------------------------" +
                    "------------");
            System.out.println("--> " + prefix);


        }
        return null;
    }




    /**
     * This method is to use auto complete to get the author.
     * @param data
     * @param scan

     * @return
     */
    public static Author autoCompleteArtist(Data data, Scanner scan) {

        //Ask the user to input the prefix, one letter at a time
        List<String> authorNames = new ArrayList<>();
        Set<Author> authors = new TreeSet<>();
        int count = 0;
        String prefix = "";
        boolean repeat = true;

        while (repeat) {
            scan.reset();
            String character = takeAWord(scan, "artist");
            //if quit is entered, quit to previous menu
            if (character.equals("quit")) {
                break;
            }
            if (character.equals("backspace")) {
                if (prefix.length() > 0) {
                    prefix = prefix.substring(0, prefix.
                            length() - 1);
                    if (prefix.length() == 0) {
                        System.out.println("-->");
                        continue;
                    }
                }
            } else if (character.equals("select")) {
                if (prefix.length() == 0) {
                    System.out.println("Please enter at " +
                            "least one letter.");
                    continue;
                } else if (authors.size() == 0) {
                    System.out.println("No match found, going" +
                            " back to searching.");
                    System.out.println("--> " + prefix);
                    continue;
                }
                System.out.println("Please select an artist " +
                        "by index, or enter 0 to continue " +
                        "searching.\n" +
                        "Enter -1 to quit to previous menu.");
                //scan the next integer
                scan.reset();
                int index = scan.nextInt();
                //make sure the index is valid
                while (index < -1 || index - 1 > count) {
                    System.out.println("Please enter a valid " +
                            "index.");
                    index = scan.nextInt();
                }
                //if the index is -1, quit to previous menu
                if (index == -1) {
                    repeat = false;
                }
                //if the index is 0, continue searching
                else if (index == 0) {
                    System.out.println("--> " + prefix);
                    continue;
                } else {
                    //if the index is valid, get the artwork from the set
                    for (Author au : authors) {
                        if (au.getName().equals(authorNames.
                                get(index - 1))) {
                            return au;
                        }
                    }
                }


            } else {
                prefix += character;
            }
            //Get the set of artwork names that match the prefix
            authors = Trie.trieForAuthor(prefix, data);
            System.out.println("-----------------------------" +
                    "--------------");
            System.out.println("Artist that match the prefix: " +
                    prefix +
                    "\nEnter \"select\" to select artwork, \nor " +
                    "\"quit\" to quit to previous menu");
            System.out.println("------------------------------" +
                    "-------------");
            authorNames = new ArrayList<>();
            //If there is no match, print "No match found"
            if (authors.size() == 0) {
                System.out.println("No match found");
            }
            //Take the first 9 matches and print them out
            else {
                count = 0;
                for (Author au : authors) {
                    if (count < 9) {
                        System.out.println((count+1) + ": " +
                                "[" + au.getName() + "]");
                        count++;
                    }
                    authorNames.add(au.getName());
                }
            }
            System.out.println("----------------------------" +
                    "---------------");
            System.out.println("--> " + prefix);
        }

        return null;
    }


}

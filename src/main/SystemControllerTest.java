package src.main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 *
 * Test if the output contains sth.
 */
public class SystemControllerTest {

    @org.junit.Test
    public void showMenuOptions() {

        SystemController sys = new SystemController();
        // Create a new output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // Test the user input
        List<String> options = new ArrayList<>();
        System.setOut(new PrintStream(out));
        options.add("1. Search for a word");
        options.add("2. Show all words");
        options.add("3. Show all words starting with a letter");
        options.add("4. Show all words ending with a letter");
        sys.showMenuOptions(options, "optional message");
        // Assert out is not empty
        assertFalse(out.toString().isEmpty());
        // Restore the original output stream
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

    }

    @org.junit.Test
    public void optionSelection() {
        SystemController sys = new SystemController();
        // Change the input stream to test the user input
        InputStream sysInBackup = System.in;
        // Create a new input stream
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        Scanner sc = new Scanner(in);
        System.setIn(in);
        // Test the user input
        // If the user input is 1, the method should return 1
        assertEquals(1, sys.optionSelection("", "quit", sc, 0, 3));
        in = new ByteArrayInputStream("0".getBytes());
        sc = new Scanner(in);
        System.setIn(in);
        assertEquals(0, sys.optionSelection("", "quit", sc, 0, 3));
        // Restore the original input stream
        System.setIn(sysInBackup);
    }

    @org.junit.Test
    public void pressEnterToContinue() {
        SystemController sys = new SystemController();
        // Change the input stream to test the user input
        InputStream sysInBackup = System.in;
        // Create a new input stream
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        //create a new output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        sys.pressEnterToContinue();
        // Assert out is not empty
        assertFalse(out.toString().isEmpty());
        // Restore the original input stream
        System.setIn(sysInBackup);
        // Restore the original output stream
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @org.junit.Test
    public void mainMenu() {
        SystemController sys = new SystemController();
        // Change the input stream to test the user input
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        Scanner sc = new Scanner(in);
        System.setIn(in);
        // Create a new output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // Test the user input
        System.setOut(new PrintStream(out));
        sys.mainMenu(sc);
        // Assert out is not empty
        assertFalse(out.toString().isEmpty());
        // Restore the original output stream
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @org.junit.Test
    public void alphabetPartSelection() {
        SystemController sys = new SystemController();
        // Change the input stream to test the user input
        InputStream sysInBackup = System.in;
        // Create a new input stream
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        Scanner sc = new Scanner(in);
        System.setIn(in);
        // Test the user input
        // If the user input is 1, the method should return 1
        assertEquals(1, sys.alphabetPartSelection(sc));
        in = new ByteArrayInputStream("0".getBytes());
        sc = new Scanner(in);
        System.setIn(in);
        assertEquals(0, sys.optionSelection("", "quit", sc, 0, 3));
        // Restore the original input stream
        System.setIn(sysInBackup);
    }

    @org.junit.Test
    public void welcomeInfo() {
        SystemController sys = new SystemController();
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        // Create a new output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        sys.welcomeInfo();
        // Assert out is not empty
        assertFalse(out.toString().isEmpty());
        // Restore the original output stream
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @org.junit.Test
    public void exitSystem() {
        SystemController sys = new SystemController();
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        // Create a new output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        sys.welcomeInfo();
        // Assert out is not empty
        assertFalse(out.toString().isEmpty());
        // Restore the original output stream
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }
}
package src.main;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class AutoCompleteUserInterFaceTest {

    @Test
    public void takeAWord() {

        // Change the input stream to test the user input
        InputStream sysInBackup = System.in;
        // Create a new input stream
        ByteArrayInputStream in = new ByteArrayInputStream("a".getBytes());
        Scanner sc = new Scanner(in);
        System.setIn(in);
        // Test the user input
        // If the user input is 1, the method should return 1
        Assert.assertEquals("a", AutoCompleteUserInterFace.takeAWord(sc, "artwork"));
        in = new ByteArrayInputStream("-".getBytes());
        sc = new Scanner(in);
        System.setIn(in);
        Assert.assertEquals("backspace", AutoCompleteUserInterFace.takeAWord(sc, "artwork"));
        in = new ByteArrayInputStream("select".getBytes());
        sc = new Scanner(in);
        System.setIn(in);
        Assert.assertEquals("select", AutoCompleteUserInterFace.takeAWord(sc, "artwork"));
        // Restore the original input stream
        System.setIn(sysInBackup);

    }

}
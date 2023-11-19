package assign01;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * This is a program that uses the image class you're writing
 * It will download an image from the internet, edit it, and save
 * a png file as 'outputImage.png'. Be sure to include the 'https://' part
 * when entering the image URL
 * Author: Ben Jones
 */
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Cropper {
    public static void main(String[] args) {
        String url;
        if (args.length > 1) { // Check if command-line arguments are present
            url = args[1]; // If there are command-line arguments, use the second argument as the input image URL
        } else {
            System.out.println("Enter input image URL"); // If there are no command-line arguments, prompt the user to enter the image URL
            var scanner = new Scanner(System.in);
            url = scanner.nextLine(); // Read the image URL from user input
        }

        try {
            var gi = new GrayscaleImage(new URL(url)); // Create a GrayscaleImage object and load the image from the specified URL
            gi.squarified().normalized().mirrored().savePNG(new File("outputImage.png")); // Process the image and save it in PNG format
        } catch (IOException ex) {
            System.out.println("Failed to download or save file: " + ex.getMessage()); // Handle possible IO exceptions
        }
    }
}


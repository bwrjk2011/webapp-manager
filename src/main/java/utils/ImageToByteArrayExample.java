package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ImageToByteArrayExample {

    public static void main(String[] args) {

        // Specify the path to the image file
        String filePath2 = "shankar.png";

        try {
            // Read the image file into a byte array
            byte[] imageBytes = readImageBytes(filePath2);
            // Write the byte array to the specified output file
            writeBytesToFile(imageBytes, "bytes.txt");
            // Write the byte array to System.out
            System.out.write(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeBytesToFile(byte[] bytes, String filePath) throws IOException {
        // Use java.nio.file.Path to create a Path object from the file path
        Path outputPath = Paths.get(filePath);

        // Write the byte array to the specified file
        Files.write(outputPath, bytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

    private static byte[] readImageBytes(String filePath) throws IOException {
        // Use java.nio.file.Path to create a Path object from the file path
        Path imagePath = Paths.get(filePath);

        // Read all bytes from the image file into a byte array
        return Files.readAllBytes(imagePath);
    }
}

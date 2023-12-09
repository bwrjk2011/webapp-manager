package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java ImageToByteArrayExample <hexData> <outputFilePath>");
            return;
        }

        String hexData = args[0];
        String outputFilePath = args[1];

        try {
            // Convert hexadecimal string to byte array
            byte[] imageBytes = hexStringToByteArray(hexData);

            // Write the byte array to the specified output file
            writeBytesToFile(imageBytes, outputFilePath);

            System.out.println("Bytes successfully written to: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    private static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }

        return data;
    }

    private static void writeBytesToFile(byte[] bytes, String filePath) throws IOException {
        // Use java.nio.file.Path to create a Path object from the file path
        Path outputPath = Paths.get(filePath);

        // Write the byte array to the specified file
        try (FileOutputStream fos = new FileOutputStream(outputPath.toFile())) {
            fos.write(bytes);
        }
    }

}

package utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderExample {

    public static void main(String[] args) {
        // Create an instance of BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Raw password entered by the user
        String rawPassword = "BridgeWeave1!";

        // Encode the password using BCrypt
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Display the results
        System.out.println("Raw Password: " + rawPassword);
        System.out.println("BCrypt Encoded Password: " + encodedPassword);

        // Verify the password
        boolean isPasswordMatch = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("Password Matches: " + isPasswordMatch);
    }
}

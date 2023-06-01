/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package img;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

/**
 *
 * @author Usuario
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        System.out.println(UUID.randomUUID().toString());
//    }
     public static String generateUniqueID(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(input.getBytes());
        String encodedHash = Base64.getEncoder().encodeToString(hash);
        return encodedHash.substring(0, 8);
    }

    public static void main(String[] args) {
        try {
            String input = "Hola mundo";
            String uniqueID = generateUniqueID(input);
            System.out.println(uniqueID);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}

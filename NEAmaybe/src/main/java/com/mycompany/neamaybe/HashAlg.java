
package com.mycompany.neamaybe;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Class representing a hash algorithm for converting input to MD5 hash
public class HashAlg {
    
    // Method to hash the input using MD5 algorithm
    public String hash(String input) throws NoSuchAlgorithmException {
        // Create an instance of MessageDigest with MD5 algorithm
        MessageDigest md = MessageDigest.getInstance("MD5");
        
        // Generate the hash for the input string
        byte[] messageDigest = md.digest(input.getBytes());

        // Convert the byte array to a positive BigInteger
        BigInteger bigInteger = new BigInteger(1, messageDigest);

        // Convert the BigInteger to a hexadecimal string
        return bigInteger.toString(16);
    }
}

/*
public class HashAlg {
    public String hash(String input) throws NoSuchAlgorithmException{
        MessageDigest m = MessageDigest.getInstance("MD5");
        byte[] message = m.digest(input.getBytes());
        BigInteger big = new BigInteger(1,message);
        return big.toString(16);
        
    }
    
}
*/
package com.ron_phenomenon.unbuddy.model.users;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public abstract class User {
  public final String email;
  public final byte[] hashedPassword;
  private static User loggedInUser;

  public User(final String email, final String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
    this.email = email;
    this.hashedPassword = hashPassword(password);
    User.loggedInUser = null;
  }

  public static void createAccount(final String email, final String password) {
  }

  public static void login(final String email, final String password) {
    User.loggedInUser = null; // TODO: actually log in
  }

  public static void logout() {
    User.loggedInUser = null;
  }

  public static User getLoggedInUser() {
    return User.loggedInUser;
  }

  public static byte[] hashPassword(final String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
    final byte[] salt = generateSalt();
    final String algorithm = "PBKDF2WithHmacSHA1";
    final int derivedKeyLength = 160; // SHA-1 generates 160 bit hashes
    final int numIterations = 20000;

    KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, numIterations, derivedKeyLength);
    SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

    return f.generateSecret(spec).getEncoded();
  }

  private static byte[] generateSalt() throws NoSuchAlgorithmException {
    SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
    // generate 8 byte salt
    byte[] salt = new byte[8];
    random.nextBytes(salt);
    return salt;
  }
}

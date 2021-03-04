package com.ron_phenomenon.unbuddy.model.users;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.ron_phenomenon.unbuddy.ron_engine.dynamo.DatabaseInterface;

public abstract class User {
  public final String email;
  public final String password;
  public final byte[] hashedPassword;
  public final byte[] salt;
  private static User loggedInUser = null;

  public User(final String email, final String password)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    this.email = email;
    this.password = password;
    final byte[] salt = generateSalt();
    this.salt = salt;
    this.hashedPassword = hashPassword(password, salt);
  }

  public User(final String email, final byte[] hashedPassword, final byte[] salt) {
    this.email = email;
    this.password = null;
    this.hashedPassword = hashedPassword;
    this.salt = salt;
  }

  public static User createAccount(final String email, final String password,
      final UserType userType) throws NoSuchAlgorithmException, InvalidKeySpecException {
    User user = DatabaseInterface.getUser(email);
    if (user == null) {
      if (userType == UserType.Student) {
        user = new Student(email, password);
      } else {
        user = new Faculty(email, password);
      }
      DatabaseInterface.storeUser(user);
    } else {
      user = null;
    }
    return user;
  }

  public static User login(final String email, final String password, final UserType userType)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    User dynamoUser = DatabaseInterface.getUser(email);
    if (dynamoUser != null) {
      // check for correct password
      if (Arrays.equals(User.hashPassword(password, dynamoUser.salt), dynamoUser.hashedPassword)) {
        User.loggedInUser = dynamoUser;
      } else {
        dynamoUser = null;
      }
    }
    return dynamoUser;
  }

  public static void logout() {
    User.loggedInUser = null;
  }

  public static User getLoggedInUser() {
    return User.loggedInUser;
  }

  public static byte[] hashPassword(final String password, final byte[] salt)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
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

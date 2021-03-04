package com.ron_phenomenon.unbuddy.model.users;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.UserItem;

public class Faculty extends User {
  public final String facultyName;

  public Faculty(final String facultyName, final String email, final String password)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    super(email, password);
    this.facultyName = facultyName;
  }

  public Faculty(final UserItem dynamoItem) {
    super(dynamoItem.getEmail(), dynamoItem.getHashedPassword(), dynamoItem.getSalt());
    facultyName = null;
  }

  public Faculty(final String email, final String password)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    super(email, password);
    this.facultyName = null;
  }

}

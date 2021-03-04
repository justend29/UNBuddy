package com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class UserItem {
  private String email;
  private byte[] hashedPassword;
  private byte[] salt;
  private int userType;
  private String enrolmentsDocument;


  @DynamoDbPartitionKey
  public String getEmail() {
    return this.email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public byte[] getHashedPassword() {
    return this.hashedPassword;
  }

  public void setHashedPassword(final byte[] hashedPassword) {
    this.hashedPassword = hashedPassword;
  }

  public int getUserType() {
    return this.userType;
  }

  public void setUserType(final int userType) {
    this.userType = userType;
  }

  public String getEnrolmentsDocument() {
    return this.enrolmentsDocument;
  }

  public void setEnrolmentsDocument(final String enrolmentsDocument) {
    this.enrolmentsDocument = enrolmentsDocument;
  }

  public byte[] getSalt() {
    return this.salt;
  }

  public void setSalt(final byte[] salt) {
    this.salt = salt;
  }

}

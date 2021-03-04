package com.ron_phenomenon.unbuddy.model.users;

public enum UserType {
  Student(0), Faculty(1);

  private int value;

  private UserType(final int value) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }
}

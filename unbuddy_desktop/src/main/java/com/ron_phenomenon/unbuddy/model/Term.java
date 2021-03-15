package com.ron_phenomenon.unbuddy.model;

public enum Term {
  Fall(0), Winter(1), Summer(2);

  private int value;

  private Term(final int value) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }
}

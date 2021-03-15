package com.ron_phenomenon.unbuddy.model.requirements;

public enum RequisiteType {
  Coreq(0), Prereq(1);

  private int value;

  private RequisiteType(final int value) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }
}

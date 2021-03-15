package com.ron_phenomenon.unbuddy.model.requirements;

public enum RequirementType {
  AdditionalRequirement(0), CourseRequirement(1), CourseTypeRequirement(2), CreditHourRequirement(
      3);

  private int value;

  private RequirementType(final int value) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }
}

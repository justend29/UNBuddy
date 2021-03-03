package com.ron_phenomenon.unbuddy.model.requirements;

import com.ron_phenomenon.unbuddy.model.users.Student;

public class CreditHourRequirement extends Requirement {
  public final int creditHours;

  public CreditHourRequirement(final String name, final RequisiteType requisiteType, final int creditHours) {
    super(name, requisiteType);
    this.creditHours = creditHours;
  }

  @Override
  boolean isSatisfiedBy(final Student student) {
    return false;
  }

}

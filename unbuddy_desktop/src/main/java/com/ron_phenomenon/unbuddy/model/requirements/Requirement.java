package com.ron_phenomenon.unbuddy.model.requirements;

import com.ron_phenomenon.unbuddy.model.users.Student;

public abstract class Requirement {
  public final String name;
  public final RequisiteType requisiteType;

  public Requirement(final String name, final RequisiteType requisiteType) {
    this.name = name;
    this.requisiteType = requisiteType;
  }

  abstract boolean isSatisfiedBy(final Student student);
}

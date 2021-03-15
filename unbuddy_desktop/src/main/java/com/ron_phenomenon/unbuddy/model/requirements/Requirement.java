package com.ron_phenomenon.unbuddy.model.requirements;

import com.ron_phenomenon.unbuddy.model.users.Student;

public abstract class Requirement {
  public final long id;
  public final RequisiteType requisiteType;

  public Requirement(final String s, final RequisiteType r) {
    this.id = 0;
    requisiteType = r;
  }

  public Requirement(final long id, final RequisiteType requisiteType) {
    this.id = id;
    this.requisiteType = requisiteType;
  }

  abstract boolean isSatisfiedBy(final Student student);
}

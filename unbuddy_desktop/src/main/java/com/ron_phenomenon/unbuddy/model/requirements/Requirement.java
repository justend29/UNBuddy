package com.ron_phenomenon.unbuddy.model.requirements;

import com.ron_phenomenon.unbuddy.model.users.Student;

public abstract class Requirement {
  public final Integer id;
  public final RequisiteType requisiteType;

  public Requirement(final Integer id, final RequisiteType requisiteType) {
    this.id = id;
    this.requisiteType = requisiteType;
  }

  public Requirement(final Integer id, final Integer requisiteType) {
    this.id = id;
    if(requisiteType == RequisiteType.Coreq.getValue()) {
      this.requisiteType = RequisiteType.Coreq;
    } else {
      this.requisiteType = RequisiteType.Prereq;
    }
  }

  public int hashCode() {
    return this.id;
  }

  public boolean isSatisfiedBy(final Student student) {
    return false;
  }
}

package com.ron_phenomenon.unbuddy.model.requirements;

import com.ron_phenomenon.unbuddy.model.users.Student;

// TODO: from data collection, resolve what classifies an 'additional
// requirement' and determine how that can be verified.
// maybe an OptionalRequirement is needed

public class AdditionalRequirement extends Requirement {

  public AdditionalRequirement(final String name, final RequisiteType requisiteType) {
    super(name, requisiteType);
  }

  @Override
  boolean isSatisfiedBy(final Student student) {
    return false;
  }

}

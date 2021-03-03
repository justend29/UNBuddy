package com.ron_phenomenon.unbuddy.model.requirements;

import java.util.HashSet;

import com.ron_phenomenon.unbuddy.model.users.Student;

public class CourseTypeRequirement extends Requirement {
  // set of course prefixes denoting the type
  // ex. ECE,SWE,ADM,CHEM
  private HashSet<String> courseTypes;

  public CourseTypeRequirement(final String name, final RequisiteType requisiteType) {
    super(name, requisiteType);
    this.courseTypes = new HashSet<>();
  }

  public CourseTypeRequirement(final String name, final RequisiteType requisiteType,
      final HashSet<String> courseTypes) {
    super(name, requisiteType);
    this.courseTypes = courseTypes;
  }

  public void addCourseType(final String type) {
    courseTypes.add(type);
  }

  public void removeCourseType(final String type) {
    courseTypes.remove(type);
  }

  @Override
  boolean isSatisfiedBy(final Student student) {
    return false;
  }

}

package com.ron_phenomenon.unbuddy.model.requirements;

import java.util.HashSet;

import com.ron_phenomenon.unbuddy.model.users.Student;
import com.ron_phenomenon.unbuddy.model.courses.Course;

public class CourseRequirement extends Requirement {
  private HashSet<Course> courseOptions;

  public CourseRequirement(final String name, final RequisiteType requisiteType) {
    super(name, requisiteType);
    this.courseOptions = new HashSet<>();
  }

  public CourseRequirement(final String name, final RequisiteType requisiteType, final HashSet<Course> courseOptions) {
    super(name, requisiteType);
    this.courseOptions = courseOptions;
  }

  public void addCourseOption(final Course course) {
    courseOptions.add(course);
  }

  public void removeCourseOption(final Course course) {
    courseOptions.remove(course);
  }

  @Override
  boolean isSatisfiedBy(final Student student) {
    return false;
  }

}

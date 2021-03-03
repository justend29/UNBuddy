package com.ron_phenomenon.unbuddy.model.courses;

import java.time.Year;

import com.ron_phenomenon.unbuddy.model.Term;

public class AuthorizedCourseOffering extends CourseOffering {
  // removed as course sessions aren't used in this prototype
  // public ArrayList<CourseSession> sessions

  public AuthorizedCourseOffering(Course course, Term term, Year year) {
    super(course, term, year);
  }
}

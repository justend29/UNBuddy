package com.ron_phenomenon.unbuddy.model.courses;

import java.time.Year;

import com.ron_phenomenon.unbuddy.model.Term;

public class CourseOffering {
  public final Course course;
  public final Term term;
  public final Year year;

  public CourseOffering(final Course course, final Term term, final Year year) {
    this.course = course;
    this.term = term;
    this.year = year;
  }
}

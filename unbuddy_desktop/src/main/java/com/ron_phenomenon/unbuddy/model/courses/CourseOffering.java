package com.ron_phenomenon.unbuddy.model.courses;

import java.time.Year;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.ron_phenomenon.unbuddy.model.Term;

public class CourseOffering {
  public final HashSet<Term> terms;
  public final HashSet<Year> years;

  public CourseOffering(final HashSet<Term> terms, final HashSet<Year> years) {
    this.terms = terms;
    this.years = years;
  }
}

package com.ron_phenomenon.unbuddy.model.courses;

import java.util.ArrayList;

import com.ron_phenomenon.unbuddy.model.requirements.Requirement;

public class Course {
  private final String name;
  private final int creditHours;
  public ArrayList<Requirement> dependencies;

  public Course(final String name, final int creditHours) {
    this.name = name;
    this.creditHours = creditHours;
  }

  public String getName() {
    return this.name;
  }

  public int getCreditHours() {
    return this.creditHours;
  }
}

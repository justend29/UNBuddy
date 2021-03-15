package com.ron_phenomenon.unbuddy.model;

import java.util.ArrayList;
import java.time.Year;

import com.ron_phenomenon.unbuddy.model.requirements.Requirement;

public class AcademicProgram {
  private final String degree;
  private final Year startYear;
  private final String programTitle;
  public ArrayList<Requirement> graduationRequirements;

  public AcademicProgram(final String degree, final String programTitle, final Year startYear) {
    this.degree = degree;
    this.programTitle = programTitle;
    this.startYear = startYear;
    this.graduationRequirements = new ArrayList<>();
  }

  public String getDegree() {
    return this.degree;
  }

  public String getProgramTitle() {
    return this.programTitle;
  }

  public Year getStartYear() {
    return this.startYear;
  }
}

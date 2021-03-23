package com.ron_phenomenon.unbuddy.model;

import java.util.ArrayList;
import java.time.Year;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.ron_phenomenon.unbuddy.model.requirements.Requirement;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.DatabaseInterface;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.AcademicProgramItem;

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

  public AcademicProgram(final AcademicProgramItem dynamoItem) {
    this.degree = dynamoItem.getDegree();
    this.programTitle = dynamoItem.getProgramTitle();
    this.startYear = null;
    this.graduationRequirements = dynamoItem.getGraduationRequirements()
            .stream()
            .map(DatabaseInterface::getRequirement)
            .collect(Collectors.toCollection(ArrayList::new));
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

  public String toString() {
    return degree;
  }

  public int hashCode() {
    return this.toString().hashCode();
  }
}

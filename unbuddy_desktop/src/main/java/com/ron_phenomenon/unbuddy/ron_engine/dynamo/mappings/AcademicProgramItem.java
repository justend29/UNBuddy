package com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings;

import java.util.Set;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class AcademicProgramItem {
  private String degree;
  private Integer startYear;
  private String programTitle;
  private Set<Integer> graduationRequirements; // ids of RequirementItem

  @DynamoDbPartitionKey
  public String getDegree() {
    return this.degree;
  }

  public void setDegree(final String degree) {
    this.degree = degree;
  }

  @DynamoDbSortKey
  public Integer getStartYear() {
    return this.startYear;
  }

  public void setStartYear(final Integer startYear) {
    this.startYear = startYear;
  }

  public String getProgramTitle() {
    return this.programTitle;
  }

  public void setProgramTitle(final String programTitle) {
    this.programTitle = programTitle;
  }

  public Set<Integer> getGraduationRequirements() {
    return this.graduationRequirements;
  }

  public void setGraduationRequirements(final Set<Integer> graduationRequirements) {
    this.graduationRequirements = graduationRequirements;
  }

  public void addGraduationRequirement(final Integer reqId) {
    this.graduationRequirements.add(reqId);
  }
}

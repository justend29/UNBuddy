package com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;

import com.ron_phenomenon.unbuddy.model.requirements.RequirementType;
import com.ron_phenomenon.unbuddy.model.requirements.RequisiteType;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class RequirementItem {
  private Integer id;
  private Integer requirementType;
  private Integer requisiteType;

  // CourseRequirement
  private Set<String> courseOptions; // courseName

  // CreditHourRequirement
  private Integer creditHours;

  // CourseTypeRequirement
  private Set<String> coursePrefixes;

  // AdditionalRequirement
  private String additionalNote;

  @DynamoDbPartitionKey
  public Integer getId() {
    return this.id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  @DynamoDbSortKey
  public Integer getRequirementType() {
    return this.requirementType;
  }

  public void setRequirementType(final Integer requirementType) {
    this.requirementType = requirementType;
  }

  public Integer getRequisiteType() {
    return this.requisiteType;
  }

  public void setRequisiteType(final Integer requisiteType) {
    this.requisiteType = requisiteType;
  }

  public Set<String> getCourseOptions() {
    return this.courseOptions;
  }

  public void setCourseOptions(final Set<String> courseOptions) {
    this.courseOptions = courseOptions;
  }

  public Integer getCreditHours() {
    return this.creditHours;
  }

  public void setCreditHours(final Integer creditHours) {
    this.creditHours = creditHours;
  }

  public Set<String> getCoursePrefixes() {
    return this.coursePrefixes;
  }

  public void setCoursePrefixes(final Set<String> coursePrefixes) {
    this.coursePrefixes = coursePrefixes;
  }


  public void setAdditionalNote(final String additionalNote) {
    this.additionalNote = additionalNote;
  }

  public String getAdditionalNote() {
    return this.additionalNote;
  }


  public void idFromCourseRequirements() {

    String reqId = courseOptions.stream().sorted().collect(Collectors.joining(","));
    reqId = String.valueOf(this.requirementType) + "," + String.valueOf(this.requisiteType) + ","
        + reqId;
    this.id = reqId.hashCode();
  }

  public void idFromCreditHourRequirement() {
    String reqId = String.valueOf(this.requirementType) + "," + String.valueOf(this.requisiteType)
        + "," + String.valueOf(this.creditHours);
    this.id = reqId.hashCode();
  }

  public void idFromTypeRequirement() {
    this.id = this.coursePrefixes.stream().collect(Collectors.joining(",")).hashCode();
  }

  @Override
  public int hashCode() {
    return (int) this.id;
  }

}

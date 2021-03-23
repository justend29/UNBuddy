package com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings;

import java.util.Set;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class CourseItem {
  private String name;
  private Integer creditHours;
  private String description;
  private Set<Integer> dependencies; // ids of RequirementItem
  // set of offerings, where each offering extends multiple year/semesters
  private Set<String> offerings; // "term(number) * 10000 + year(4 digits),..."

  @DynamoDbPartitionKey
  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setDependencies(final Set<Integer> dependencies) {
    this.dependencies = dependencies;
  }

  public Set<Integer> getDependencies() {
    return this.dependencies;
  }

  public Set<String> getOfferings() {
    return this.offerings;
  }

  public void setOfferings(final Set<String> offerings) {
    this.offerings = offerings;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }

  public Integer getCreditHours() {
    return this.creditHours;
  }

  public void setCreditHours(final Integer creditHours) {
    this.creditHours = creditHours;
  }

}

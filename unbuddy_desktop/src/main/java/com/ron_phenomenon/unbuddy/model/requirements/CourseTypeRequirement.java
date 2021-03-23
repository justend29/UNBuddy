package com.ron_phenomenon.unbuddy.model.requirements;

import java.util.HashSet;
import java.util.Locale;
import java.util.stream.Collectors;

import com.ron_phenomenon.unbuddy.model.users.Student;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.RequirementItem;

public class CourseTypeRequirement extends Requirement {
  // set of course prefixes denoting the type
  // ex. ECE,SWE,ADM,CHEM
  private HashSet<String> courseTypes;

  public CourseTypeRequirement(final RequirementItem dynamoItem) {
    super(dynamoItem.getId(), dynamoItem.getRequisiteType());
    this.courseTypes = new HashSet<>(dynamoItem.getCoursePrefixes());
  }

  public void addCourseType(final String type) {
    courseTypes.add(type);
  }

  public void removeCourseType(final String type) {
    courseTypes.remove(type);
  }

  @Override
  public boolean isSatisfiedBy(final Student student) {
    final HashSet<String> completedPrefixes = student.completed
            .stream()
            .map(courseOffering -> courseOffering.courseName)
            .map(String::toLowerCase)
            .collect(Collectors.toCollection(HashSet::new));

    if(this.requisiteType == RequisiteType.Prereq) {
      for(final String reqPrefix : courseTypes) {
        if(completedPrefixes.contains(reqPrefix)) {
          return true;
        }
      }
    } else  {
      final HashSet<String> enrolledPrefixes = student.currentlyEnrolled
              .stream()
              .map(courseOffering -> courseOffering.courseName)
              .map(String::toLowerCase)
              .collect(Collectors.toCollection(HashSet::new));

      for(final String reqPrefix : courseTypes) {
        if(completedPrefixes.contains(reqPrefix) || enrolledPrefixes.contains(reqPrefix)) {
          return true;
        }
      }
    }

    return false;
  }

  public String toString() {
    return String.join(",", courseTypes);
  }

}

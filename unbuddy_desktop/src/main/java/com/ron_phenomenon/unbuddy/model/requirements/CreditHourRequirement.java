package com.ron_phenomenon.unbuddy.model.requirements;

import com.ron_phenomenon.unbuddy.model.users.Student;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.DatabaseInterface;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.RequirementItem;

import java.util.Objects;

public class CreditHourRequirement extends Requirement {
  public final int creditHours;

  public CreditHourRequirement(final Integer id, final RequisiteType requisiteType, final int creditHours) {
    super(id, requisiteType);
    this.creditHours = creditHours;
  }

  public CreditHourRequirement(final RequirementItem dynamoItem) {
    super(dynamoItem.getId(), dynamoItem.getRequisiteType());
    this.creditHours = dynamoItem.getCreditHours();
  }

  @Override
  public boolean isSatisfiedBy(final Student student) {
      final int finishedCh = student.completed
              .stream()
              .map(courseOffering -> courseOffering.courseName)
              .map(DatabaseInterface::getCourse)
              .filter(Objects::nonNull)
              .map(course -> course.creditHours)
              .reduce(0, Integer::sum);

    if(this.requisiteType == RequisiteType.Prereq) {
      return finishedCh >= creditHours;
    } else {
      final int enrolledCh = student.currentlyEnrolled
              .stream()
              .map(courseOffering -> courseOffering.courseName)
              .map(DatabaseInterface::getCourse)
              .filter(Objects::nonNull)
              .map(course -> course.creditHours)
              .reduce(0, Integer::sum);
      return finishedCh + enrolledCh >= creditHours;
    }
  }

}

package com.ron_phenomenon.unbuddy.model.requirements;

import com.ron_phenomenon.unbuddy.model.users.Student;
import com.ron_phenomenon.unbuddy.model.requirements.RequisiteType;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.RequirementItem;


public class AdditionalRequirement extends Requirement {
  private String additionalNote;

  public AdditionalRequirement(final RequirementItem dynamoItem) {
    super(dynamoItem.getId(), dynamoItem.getRequisiteType());
    this.additionalNote = dynamoItem.getAdditionalNote();
  }

  @Override
  public boolean isSatisfiedBy(final Student student) {
    return true;
  }

}

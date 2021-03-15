package com.ron_phenomenon.unbuddy.ron_engine.dynamo.data_loader;

import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import com.ron_phenomenon.unbuddy.model.requirements.RequirementType;
import com.ron_phenomenon.unbuddy.model.requirements.RequisiteType;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.DatabaseInterface;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.AcademicProgramItem;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.RequirementItem;

public class AcademicProgramItemsParser {
  private final static int degreeRow = 2;
  private final static int degreeCol = 0;
  private final static int titleRow = 2;
  private final static int titleCol = 2;
  private final static int creditHourRow = 1;
  private final static int creditHourCol = 4;
  private final static int reqsRow = 6;
  private final static int nameCol = 0;
  private final static int typeCol = 1;
  private final static int courseCol = 2;

  public static HashSet<RequirementItem> parseFrom(final String fileName)
      throws FileNotFoundException {
    final File requirementFile = new File(fileName);
    final Scanner requirementsReader = new Scanner(requirementFile);
    HashSet<RequirementItem> requirements = new HashSet<>();

    AcademicProgramItem program = new AcademicProgramItem();
    program.setGraduationRequirements(new HashSet<Integer>());

    for (int lineCount = 0; requirementsReader.hasNextLine(); ++lineCount) {
      final String reqLine = requirementsReader.nextLine();
      final String[] lineItems = reqLine.split("\\$");

      if (lineCount == creditHourRow) {
        RequirementItem chReq = new RequirementItem();
        chReq.setRequirementType(RequirementType.CreditHourRequirement.getValue());
        chReq.setRequisiteType(RequisiteType.Prereq.getValue());
        chReq.setCreditHours(Integer.valueOf(lineItems[creditHourCol]));
        chReq.idFromCreditHourRequirement();
        program.addGraduationRequirement(chReq.getId());
        requirements.add(chReq);
      }
      if (lineCount == degreeRow) {
        program.setDegree(lineItems[degreeCol].trim());
      }
      if (lineCount == titleRow) {
        program.setProgramTitle(lineItems[titleCol].trim());
      }

      // Read and populate graduation requirements
      if (lineCount >= reqsRow) {
        final String reqName = lineItems[nameCol];
        final String typeReqStr = lineItems[typeCol];
        String firstCourseReq = null;
        if (lineItems.length > courseCol) {
          firstCourseReq = lineItems[courseCol];
        }

        // Course Type Requirement
        if (!typeReqStr.isEmpty()) {
          RequirementItem typeReq = new RequirementItem();
          typeReq.setCoursePrefixes(parseTypeRequirement(typeReqStr));
          typeReq.setRequirementType(RequirementType.CourseTypeRequirement.getValue());
          typeReq.setRequisiteType(RequisiteType.Prereq.getValue());
          typeReq.idFromTypeRequirement();
          program.addGraduationRequirement(typeReq.getId());
          requirements.add(typeReq);
        }

        // Course Requirement
        if (firstCourseReq != null && !firstCourseReq.isEmpty()) {
          RequirementItem courseReq = new RequirementItem();
          courseReq.setRequirementType(RequirementType.CourseRequirement.getValue());
          courseReq.setRequisiteType(RequisiteType.Prereq.getValue());
          HashSet<String> courses = new HashSet<>();
          for (int courseIdx = courseCol; courseIdx < lineItems.length; ++courseIdx) {
            courses.add(lineItems[courseIdx]);
          }
          courseReq.setCourseOptions(courses);
          courseReq.idFromCourseRequirements();
          program.addGraduationRequirement(courseReq.getId());
          requirements.add(courseReq);
        }
      }
    }

    DatabaseInterface.writeAcademicProgramItem(program);
    requirementsReader.close();
    return requirements;
  }

  private static HashSet<String> parseTypeRequirement(final String typeReq) {
    if (typeReq.isEmpty()) {
      return null;
    }
    return Arrays.stream(typeReq.split(",")).map(r -> r.trim())
        .collect(Collectors.toCollection(HashSet::new));
  }

}

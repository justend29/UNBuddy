package com.ron_phenomenon.unbuddy.ron_engine.dynamo.data_loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Collectors;
import com.ron_phenomenon.unbuddy.model.Term;
import com.ron_phenomenon.unbuddy.model.requirements.RequirementType;
import com.ron_phenomenon.unbuddy.model.requirements.RequisiteType;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.DatabaseInterface;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.CourseItem;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.RequirementItem;

public class CourseItemsParser {
    public final static int dataStartRow = 3;

    public final static int nameCol = 0;
    public final static int titleCol = 1;
    public final static int prereqCol = 2;
    public final static int coreqCol = 3;
    public final static int creditHourReqCol = 4;
    public final static int additionalPrereqCol = 5;
    public final static int creditHourCol = 6;
    public final static int offeringCol = 7;

    public static HashSet<RequirementItem> parseFrom(final String fileName)
            throws FileNotFoundException {
        final File courses = new File(fileName);
        final Scanner courseReader = new Scanner(courses);
        final HashSet<RequirementItem> dependencies = new HashSet<>();

        // Each row in the CSV file (each course)
        for (int lineCount = 0; courseReader.hasNextLine(); ++lineCount) {
            final String courseLine = courseReader.nextLine();
            System.out.println(courseLine);
            if (lineCount >= dataStartRow) {
                final String[] lineItems = courseLine.split("\\$");
                System.out.println("going with length: " + lineItems.length);

                final String name = lineItems[nameCol];
                final String title = lineItems[titleCol];
                final String prereqs = lineItems[prereqCol];
                final String coreqs = lineItems[coreqCol];
                final String creditHourReq = lineItems[creditHourReqCol];
                final String additionalPrereqs = lineItems[additionalPrereqCol];
                final String creditHours = lineItems[creditHourCol];

                // Course Requirements
                final HashSet<RequirementItem> coursePrereqs =
                        parseCourseRequirements(prereqs, RequisiteType.Prereq);
                final HashSet<RequirementItem> courseCoreqs =
                        parseCourseRequirements(coreqs, RequisiteType.Coreq);

                // Credit Hour Requirement
                RequirementItem chReq = null;
                if (!creditHourReq.isEmpty()) {
                    chReq = new RequirementItem();
                    chReq.setRequirementType(RequirementType.CreditHourRequirement.getValue());
                    chReq.setRequisiteType(RequisiteType.Prereq.getValue());
                    chReq.setCreditHours(Integer.parseInt(creditHourReq));
                    chReq.idFromCreditHourRequirement();
                }

                // Additional Reqs
                RequirementItem additionalReq = null;
                if (!additionalPrereqs.isEmpty()) {
                    additionalReq = new RequirementItem();
                    additionalReq
                            .setRequirementType(RequirementType.AdditionalRequirement.getValue());
                    additionalReq.setRequisiteType(RequisiteType.Prereq.getValue());
                    additionalReq.setAdditionalNote(additionalPrereqs);
                    additionalReq.setId(additionalPrereqs.hashCode());
                }

                // Sum all Reqs
                HashSet<RequirementItem> reqs = new HashSet<>();
                reqs.addAll(coursePrereqs);
                reqs.addAll(courseCoreqs);
                if (additionalReq != null) {
                    reqs.add(additionalReq);
                }
                if (chReq != null) {
                    reqs.add(chReq);
                }

                HashSet<Integer> reqIds = reqs.stream().map(req -> req.getId())
                        .collect(Collectors.toCollection(HashSet::new));

                // Create CourseItem
                final CourseItem course = new CourseItem();
                course.setName(name);
                course.setCreditHours(Integer.valueOf(creditHours));
                course.setDescription(title);
                if (reqIds.isEmpty()) {
                    reqIds = null;
                }
                course.setDependencies(reqIds);
                final HashSet<String> offerings = parseCourseOfferings(lineItems);
                course.setOfferings(offerings);

                // Write CourseItem to db
                DatabaseInterface.writeCourseItem(course);

                // Add this course reqs to list of all reqs
                dependencies.addAll(reqs);
            }
        }
        courseReader.close();
        return dependencies;
    }

    private static HashSet<RequirementItem> parseCourseRequirements(final String prereqs,
            final RequisiteType reqType) {
        if (prereqs.isEmpty()) {
            return new HashSet<>();
        }
        return Arrays.stream(prereqs.split(";")).map(courseRequirement -> courseRequirement.trim())
                .map(courseRequirement -> {
                    final HashSet<String> courseOptionNames = Arrays
                            .stream(courseRequirement.split("or")).map(course -> course.trim())
                            .collect(Collectors.toCollection(HashSet::new));

                    final RequirementItem req = new RequirementItem();
                    req.setCourseOptions(courseOptionNames);
                    req.setRequisiteType(reqType.getValue());
                    req.setRequirementType(RequirementType.CourseRequirement.getValue());
                    req.idFromCourseRequirements();
                    return req;
                }).collect(Collectors.toCollection(HashSet::new));
    }

    private static HashSet<String> parseCourseOfferings(final String[] lineItems) {
        if (lineItems.length <= offeringCol) { // no offerings
            return null;
        }
        HashSet<String> offerings = new HashSet<>();
        // For each offering
        for (int i = offeringCol + 1; i < lineItems.length; i += 2) {
            // term(number) * 10000 + year(4 digits)
            ArrayList<Integer> terms =
                    Arrays.stream(lineItems[i].split(";")).map(term -> term.trim()).map(term -> {
                        Term t = Term.Summer;
                        if ("Winter".equals(term)) {
                            t = Term.Winter;
                        } else if ("Fall".equals(term)) {
                            t = Term.Fall;
                        }
                        return t.getValue() * 10000;
                    }).collect(Collectors.toCollection(ArrayList::new));

            final ArrayList<Integer> years = Arrays.stream(lineItems[i + 1].split(";"))
                    .map(year -> year.trim()).map(year -> Integer.valueOf(year))
                    .collect(Collectors.toCollection(ArrayList::new));

            String offering = new String();
            for (int offeringIndex = 0; offeringIndex < terms.size(); ++offeringIndex) {
                final int offeringNum = terms.get(offeringIndex) + years.get(offeringIndex);
                offering += String.valueOf(offeringNum);
                if (offeringIndex < terms.size() - 1) {
                    offering += ",";
                }
            }
            offerings.add(offering);
        }
        return offerings;
    }



}

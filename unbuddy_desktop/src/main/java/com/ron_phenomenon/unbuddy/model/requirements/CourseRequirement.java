package com.ron_phenomenon.unbuddy.model.requirements;

import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ron_phenomenon.unbuddy.model.users.Student;
import com.ron_phenomenon.unbuddy.model.courses.Course;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.DatabaseInterface;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.RequirementItem;

public class CourseRequirement extends Requirement {
    private HashSet<Course> courseOptions;

    public CourseRequirement(final Integer id, final RequisiteType requisiteType) {
        super(id, requisiteType);
        this.courseOptions = new HashSet<>();
    }

    public CourseRequirement(final RequirementItem dynamoItem) {
        super(dynamoItem.getId(), dynamoItem.getRequisiteType());
        this.courseOptions = dynamoItem.getCourseOptions() == null ? new HashSet<>() :
                dynamoItem.getCourseOptions()
                        .stream()
                        .map(DatabaseInterface::getCourse)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toCollection(HashSet::new));
    }

    public void addCourseOption(final Course course) {
        courseOptions.add(course);
    }

    public void removeCourseOption(final Course course) {
        courseOptions.remove(course);
    }

    @Override
    public boolean isSatisfiedBy(final Student student) {
        if(this.requisiteType == RequisiteType.Coreq) {
            for (final Course c : courseOptions) {
                if (student.hasCompletedCourse(c.name) || student.isEnrolledInCourse(c.name)) {
                    return true;
                }
            }
        } else { // prereq
            for (final Course c : courseOptions) {
                if (student.hasCompletedCourse(c.name)) {
                    return true;
                }
            }
        }
        return false;
    }

}

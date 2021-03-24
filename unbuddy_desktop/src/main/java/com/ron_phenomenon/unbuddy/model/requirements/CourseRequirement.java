package com.ron_phenomenon.unbuddy.model.requirements;

import java.util.HashSet;

import com.ron_phenomenon.unbuddy.model.users.Student;
import com.ron_phenomenon.unbuddy.model.courses.Course;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.RequirementItem;

public class CourseRequirement extends Requirement {
    private HashSet<String> courseOptions; // courseNames

    public CourseRequirement(final Integer id, final RequisiteType requisiteType) {
        super(id, requisiteType);
        this.courseOptions = new HashSet<>();
    }

    public CourseRequirement(final RequirementItem dynamoItem) {
        super(dynamoItem.getId(), dynamoItem.getRequisiteType());
        this.courseOptions = dynamoItem.getCourseOptions() == null ? new HashSet<>() :
                new HashSet<>(dynamoItem.getCourseOptions());
    }

    public void addCourseOption(final Course course) {
        courseOptions.add(course.name);
    }

    public void removeCourseOption(final Course course) {
        courseOptions.remove(course.name);
    }

    @Override
    public boolean isSatisfiedBy(final Student student) {
        if(this.requisiteType == RequisiteType.Coreq) {
            for (final String cName : courseOptions) {
                if (student.hasCompletedCourse(cName) || student.isEnrolledInCourse(cName)) {
                    return true;
                }
            }
        } else { // prereq
            for (final String cName : courseOptions) {
                if (student.hasCompletedCourse(cName)) {
                    return true;
                }
            }
        }
        return false;
    }

}

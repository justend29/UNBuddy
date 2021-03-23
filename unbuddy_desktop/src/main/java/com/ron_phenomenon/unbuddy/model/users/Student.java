package com.ron_phenomenon.unbuddy.model.users;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ron_phenomenon.unbuddy.model.courses.Course;
import com.ron_phenomenon.unbuddy.model.courses.CourseOffering;
import com.ron_phenomenon.unbuddy.model.AcademicProgram;
import com.ron_phenomenon.unbuddy.model.requirements.AdditionalRequirement;
import com.ron_phenomenon.unbuddy.model.requirements.Requirement;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.DatabaseInterface;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.UserItem;

public class Student extends User {
    public ArrayList<CourseOffering> completed;
    public ArrayList<CourseOffering> currentlyEnrolled;
    public ArrayList<AcademicProgram> programs;

    public Student(final String email, final String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        super(email, password);
        this.completed = new ArrayList<>();
        this.currentlyEnrolled = new ArrayList<>();
        this.programs = new ArrayList<>();
    }

    public Student(final UserItem dynamoItem) {
        super(dynamoItem.getEmail(), dynamoItem.getHashedPassword(), dynamoItem.getSalt());
        completed = dynamoItem.getCompleted() == null ? new ArrayList<>() :
                dynamoItem.getCompleted()
                        .stream()
                        .map(offeringStr -> {
                            String[] offeringFields = offeringStr.split(",");
                            String courseName = offeringFields[0];
                            String termStr = offeringFields[1];
                            String yearStr = offeringFields[2];
                            Course c = DatabaseInterface.getCourse(courseName);
                            if (c == null) {
                                return null;
                            }
                            return c.getCourseOffering(termStr, yearStr);
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toCollection(ArrayList::new));

        currentlyEnrolled = dynamoItem.getCurrentlyEnrolled() == null ? new ArrayList<>() :
                dynamoItem.getCurrentlyEnrolled()
                        .stream()
                        .map(offeringStr -> {
                            String[] offeringFields = offeringStr.split(",");
                            String courseName = offeringFields[0];
                            String termStr = offeringFields[1];
                            String yearStr = offeringFields[2];
                            Course c = DatabaseInterface.getCourse(courseName);
                            if (c == null) {
                                return null;
                            }
                            return c.getCourseOffering(termStr, yearStr);
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toCollection(ArrayList::new));

        programs = dynamoItem.getPrograms() == null ? new ArrayList<>() :
                dynamoItem.getPrograms()
                        .stream()
                        .map(DatabaseInterface::getProgram)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toCollection(ArrayList::new));
    }

    public static void enrolInAcademicProgram(final AcademicProgram program) {
        Student s = (Student) User.getLoggedInUser();

        // student isn't already enrolled in program
        if (s.programs != null && s.programs.contains(program)) {
            throw new EnrolmentException("Already enrolled in program");
        }
        DatabaseInterface.enrolInAcademicProgram(s, program);
    }

    public static void disenrolFromAcademicProgram(final AcademicProgram program) {
        Student s = (Student) User.getLoggedInUser();

        // student isn't enrolled in program
        if (s.programs == null || !s.programs.contains(program)) {
            throw new EnrolmentException("Cannot disenrol from a program you're not currently enrolled in");
        }

        DatabaseInterface.disenrolFromAcademicProgram(s, program);
    }

    // returns requirement that prevented enrolment, or null if enrolment was made
    public static Requirement enrolInCourseOffering(final CourseOffering offering) {
        Student s = (Student) User.getLoggedInUser();

        // student is already enrolled in course
        if (s.currentlyEnrolled != null && s.currentlyEnrolled.contains(offering)) {
            throw new EnrolmentException("Already enrolled in course");
        }

        // student has already completed course
        if (s.completed != null && s.completed.contains(offering)) {
            throw new EnrolmentException("Already completed course");
        }

        // student meets course requirements
        Course c = DatabaseInterface.getCourse(offering.courseName);
        assert c != null;
        for(Requirement r: c.dependencies) {
            if(!r.isSatisfiedBy((Student) User.getLoggedInUser())) {
                return r;
            }
        }

        DatabaseInterface.enrolInCourseOffering(s, offering);
        return null;
    }

    // null if student can graduate, or requirement preventing graduation
    public static Requirement requirementPreventingGraduation(final AcademicProgram program) {
        for(Requirement r : program.graduationRequirements) {
            if(!r.isSatisfiedBy((Student) User.getLoggedInUser())) {
                return r;
            }
        }
        return null;
    }

    public static void disenrolFromCourseOffering(final CourseOffering offering) {
        Student s = (Student) User.getLoggedInUser();

        // student isn't enrolled in course
        if (s.currentlyEnrolled == null || !s.currentlyEnrolled.contains(offering)) {
            throw new EnrolmentException("Cannot disenrol from a course you're not currently enrolled in");
        }

        DatabaseInterface.disenrolFromCourseOffering(s, offering);
    }

    public boolean isEnrolledInCourse(final String courseName) {
        for (final CourseOffering offering : this.currentlyEnrolled) {
            if (offering.courseName.equals(courseName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCompletedCourse(final String courseName) {
        for (final CourseOffering offering : this.completed) {
            if (offering.courseName.equals(courseName)) {
                return true;
            }
        }
        return false;
    }




    public boolean complete(final CourseOffering offering) {
        return false;
    }


}

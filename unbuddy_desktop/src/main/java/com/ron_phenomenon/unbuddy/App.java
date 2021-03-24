package com.ron_phenomenon.unbuddy;

import com.ron_phenomenon.unbuddy.model.AcademicProgram;
import com.ron_phenomenon.unbuddy.model.Term;
import com.ron_phenomenon.unbuddy.model.courses.Course;
import com.ron_phenomenon.unbuddy.model.courses.CourseOffering;
import com.ron_phenomenon.unbuddy.model.users.EnrolmentException;
import com.ron_phenomenon.unbuddy.model.users.Student;
import com.ron_phenomenon.unbuddy.model.users.User;
import com.ron_phenomenon.unbuddy.model.users.UserType;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.DatabaseInterface;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.data_loader.DataLoader;
import com.ron_phenomenon.util.Pair;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.time.Instant;
import java.time.Year;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        DatabaseInterface.connect();


        // Data Loader Mode
        // DataLoader.run();

        // Desktop Mode
        // create user use case
        try {
            final User testUser =
                    User.createAccount("testCreate2", "testPassword", UserType.Student);
            if (testUser == null) {
                System.err.println("Account already exists");
            }
        } catch (Exception e) {
            System.out.println("failure creating new user" + e.getMessage() + e.getStackTrace());
        }

        // login use case
        try {
            final User testUser2 = User.login("testCreate2", "testPassword", UserType.Student);
            if (testUser2 == null) {
                System.err.println("Incorrect email or password");
            }
        } catch (Exception e) {
            System.out.println("bad" + e.getMessage());
            e.printStackTrace();
        }

        // Enrol in program use case
        ArrayList<AcademicProgram> programs = DatabaseInterface.getPrograms();
        try {
            // enrol in random program
            Student.enrolInAcademicProgram(programs.get(0));
        } catch (EnrolmentException e) {
            System.err.println(e.getMessage());
        }

        // Disenrol in program use case
        try {
            // disenrol from the same random program
            Student.disenrolFromAcademicProgram(programs.get(0));
        } catch (EnrolmentException e) {
            System.err.println(e.getMessage());
        }

        // Get all courses
        long start = Instant.now().toEpochMilli();
        ArrayList<Course> allCourses = null;
        try {
            allCourses = DatabaseInterface.getCourses();
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
        }
        long diff = Instant.now().toEpochMilli() - start;
        System.out.println("Retreiving all took: " + diff);

        // Get course offering in term
        start = Instant.now().toEpochMilli();
        Pair<Term, Year> offeringTime = new Pair<>(Term.Fall, Year.of(2021));
        ArrayList<CourseOffering> offerings = DatabaseInterface.getCourseOfferingsInTerm(offeringTime);
        diff = Instant.now().toEpochMilli() - start;
        System.out.println("Retreiving offerings took: " + diff);
        System.out.println("Num offerings: " + offerings.size());

        System.out.println("Hello World!");
    }

}

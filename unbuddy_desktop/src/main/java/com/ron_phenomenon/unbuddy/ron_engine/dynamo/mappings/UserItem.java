package com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings;

import java.util.Set;
import java.util.stream.Collectors;

import com.ron_phenomenon.unbuddy.model.AcademicProgram;
import com.ron_phenomenon.unbuddy.model.courses.CourseOffering;
import com.ron_phenomenon.unbuddy.model.users.Faculty;
import com.ron_phenomenon.unbuddy.model.users.Student;
import com.ron_phenomenon.unbuddy.model.users.UserType;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class UserItem {
    private String email;
    private byte[] hashedPassword;
    private byte[] salt;
    private int userType;
    private Set<String> completed; // "courseName,term,year" -> defines a course offering
    private Set<String> currentlyEnrolled; // "courseName#term(number) * 10000 + year(4
    // digits),..."
    private Set<String> programs; // "degree"
    private String facultyName; // faculty only

    @DynamoDbPartitionKey
    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public byte[] getHashedPassword() {
        return this.hashedPassword;
    }

    public void setHashedPassword(final byte[] hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public int getUserType() {
        return this.userType;
    }

    public void setUserType(final int userType) {
        this.userType = userType;
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public void setSalt(final byte[] salt) {
        this.salt = salt;
    }

    public Set<String> getCompleted() {
        return this.completed;
    }

    public void setCompleted(final Set<String> completed) {
        this.completed = completed;
    }

    public Set<String> getCurrentlyEnrolled() {
        return this.currentlyEnrolled;
    }

    public void setCurrentlyEnrolled(final Set<String> currentlyEnrolled) {
        this.currentlyEnrolled = currentlyEnrolled;
    }

    public Set<String> getPrograms() {
        return this.programs;
    }

    public void setPrograms(final Set<String> programs) {
        this.programs = programs;
    }

    public String getFacultyName() {
        return this.facultyName;
    }

    public void setFacultyName(final String facultyName) {
        this.facultyName = facultyName;
    }

    public UserItem(final Student s) {
        this.email = s.email;
        this.hashedPassword = s.hashedPassword;
        this.salt = s.salt;
        this.userType = UserType.Student.getValue();
        this.completed = s.completed.stream().map(CourseOffering::toString).collect(Collectors.toSet());
        if(this.completed.isEmpty()) {this.completed = null; }
        this.currentlyEnrolled = s.currentlyEnrolled.stream().map(CourseOffering::toString).collect(Collectors.toSet());
        if(this.currentlyEnrolled.isEmpty()) { this.currentlyEnrolled = null; }
        this.programs = s.programs.stream().map(AcademicProgram::toString).collect(Collectors.toSet());
        if(this.programs.isEmpty()) { this.programs = null; }
    }

    public UserItem(final Faculty f) {
        this.email = f.email;
        this.hashedPassword = f.hashedPassword;
        this.salt = f.salt;
        this.userType = UserType.Faculty.getValue();
        this.facultyName = f.facultyName;
    }

    public UserItem() {
    }
}

package com.ron_phenomenon.unbuddy.model.users;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.Map;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.ron_phenomenon.unbuddy.model.courses.CourseOffering;
import com.ron_phenomenon.unbuddy.model.AcademicProgram;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.UserItem;

public class Student extends User {
  HashSet<CourseOffering> completed;
  HashSet<CourseOffering> currentlyEnrolled;
  HashSet<AcademicProgram> programs;

  public Student(final String email, final String password)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    super(email, password);
    completed = new HashSet<>();
    currentlyEnrolled = new HashSet<>();
    programs = new HashSet<>();
  }

  public Student(final UserItem dynamoItem) {
    super(dynamoItem.getEmail(), dynamoItem.getHashedPassword(), dynamoItem.getSalt());
    completed = new HashSet<>();
    currentlyEnrolled = new HashSet<>();
    programs = new HashSet<>();

    // Parse stored document of enrolments into Map
    Gson gson = new Gson();
    Type mapType = new TypeToken<Map<String, Object>>() {
    }.getType();
    Map<String, Object> mappedDynamoItem =
        gson.fromJson(dynamoItem.getEnrolmentsDocument(), mapType);

    // Populate enrolments from parsed Map
    /*
     * for (Map.Entry<String, Object> program : mappedDynamoItem.entrySet()) { final String
     * programDegree = program.getKey(); final ProgramTerms terms = (ProgramTerms)
     * program.getValue();
     *
     * }
     */
  }

  public void enrol(final CourseOffering offering) {
    this.currentlyEnrolled.add(offering);
  }

  public void enrol(final AcademicProgram program) {
    this.programs.add(program);
  }

  public boolean disenrol(final CourseOffering offering) {
    return this.currentlyEnrolled.remove(offering);
  }

  public boolean disenrol(final AcademicProgram program) {
    return this.programs.remove(program);
  }

  public boolean complete(final CourseOffering offering) {
    final boolean wasEnrolled = this.disenrol(offering);
    if (wasEnrolled) {
      this.completed.add(offering);
    }
    return wasEnrolled;
  }

  public String getEnrolmentsDocument() {
    return "";
  }

  public void setEnrolments(final String enrolmentsDocument) {
    Gson gson = new Gson();
    Map map = gson.fromJson(enrolmentsDocument, Map.class);
  }

}

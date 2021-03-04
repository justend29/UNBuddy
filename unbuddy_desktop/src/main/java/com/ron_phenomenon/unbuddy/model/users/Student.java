package com.ron_phenomenon.unbuddy.model.users;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.ron_phenomenon.unbuddy.model.courses.CourseOffering;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.UserItem;

public class Student extends User {
  private class TermCourseOfferings {
    public HashSet<CourseOffering> termOfferings;
  }

  private class ProgramTerms {
    public ArrayList<TermCourseOfferings> programTerms;
  }

  // enroled program degree to program terms
  private HashMap<String, ProgramTerms> enrolments;

  public Student(final String email, final String password)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    super(email, password);
    enrolments = new HashMap<>();
  }

  public Student(final UserItem dynamoItem) {
    super(dynamoItem.getEmail(), dynamoItem.getHashedPassword(), dynamoItem.getSalt());
    enrolments = new HashMap<>();

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

  public String getEnrolmentsDocument() {
    return "";
  }

  public void setEnrolments(final String enrolmentsDocument) {
    Gson gson = new Gson();
    Map map = gson.fromJson(enrolmentsDocument, Map.class);
  }

}

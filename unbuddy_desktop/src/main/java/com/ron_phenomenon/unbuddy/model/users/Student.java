package com.ron_phenomenon.unbuddy.model.users;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.ron_phenomenon.unbuddy.model.courses.CourseOffering;
import com.ron_phenomenon.unbuddy.model.AcademicProgram;

public class Student extends User {
  private class TermCourseOfferings {
    public HashSet<CourseOffering> termOfferings;
  }

  private class ProgramTerms {
    public ArrayList<TermCourseOfferings> programTerms;
  }

  private HashMap<AcademicProgram, ProgramTerms> enrolments;

  public Student(final String email, final String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
    super(email, password);
    enrolments = new HashMap<>();
  }

}

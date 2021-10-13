package com.ron_phenomenon.unbuddy.ron_engine.dynamo;

import com.ron_phenomenon.unbuddy.model.AcademicProgram;
import com.ron_phenomenon.unbuddy.model.Term;
import com.ron_phenomenon.unbuddy.model.courses.Course;
import com.ron_phenomenon.unbuddy.model.courses.CourseOffering;
import com.ron_phenomenon.unbuddy.model.requirements.*;
import com.ron_phenomenon.unbuddy.model.users.*;
import com.ron_phenomenon.util.Pair;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.AcademicProgramItem;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.CourseItem;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.RequirementItem;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.UserItem;

public class DatabaseInterface {
  private static DynamoDbEnhancedClient client;

  private static final String accessKey = "";
  private static final String secretAccessKey = "";

  // Database Cache
  private static HashMap<String, User> cachedUsers = new HashMap<>(); // email -> User
  private static HashMap<String, Course> cachedCourses = new HashMap<>(); // courseName -> Course
  private static HashMap<Integer, Requirement> cachedRequirements = new HashMap<>(); // reqId to Requirement
  private static HashMap<String, AcademicProgram> cachedPrograms = new HashMap<String, AcademicProgram>(); // degree to Academic program

  public static void connect() {
    if (client == null) {
      AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretAccessKey);

      DynamoDbClient normalClient = DynamoDbClient.builder().region(Region.CA_CENTRAL_1)
          .credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build();

      client = DynamoDbEnhancedClient.builder().dynamoDbClient(normalClient).build();
    }
  }

  // ========= USER ============
  public static User getUser(final String email)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    User result = cachedUsers.get(email);
    if (result != null) {
      return result;
    }

    try {
      // get user item from dynamo
      DynamoDbTable<UserItem> usersTable =
          client.table("Users", TableSchema.fromBean(UserItem.class));
      Key key = Key.builder().partitionValue(email).build();
      final UserItem dynamoUser = usersTable.getItem(key);
      if (dynamoUser == null) {
        return null;
      }

      // construct user from item
      if (dynamoUser.getUserType() == UserType.Student.getValue()) {
        result = new Student(dynamoUser);
      } else {
        result = new Faculty(dynamoUser);
      }

      // store user in cache
      cachedUsers.put(result.email, result);
    } catch (DynamoDbException e) {
      System.err.println("dynamo exception" + e.getMessage());
    }
    return result;
  }

  public static void storeUser(final User user) {
    try {
      DynamoDbTable<UserItem> usersTable =
          client.table("Users", TableSchema.fromBean(UserItem.class));

      UserItem item;
      if (user instanceof Student) {
          item = new UserItem((Student)user);
      } else {
        item = new UserItem((Faculty) user);
      }
      usersTable.putItem(item);

      cachedUsers.put(user.email, user);
    } catch (DynamoDbException e) {
      System.err.println("failed putting" + e.getMessage());
    }
  }

  // ================ Course ==================
  public static Course getCourse(final String courseName) {
    Course result = cachedCourses.get(courseName);
    if (result != null) {
      return result;
    }

    try {
      // get course item from dynamo
      DynamoDbTable<CourseItem> coursesTable =
              client.table("Courses", TableSchema.fromBean(CourseItem.class));
      Key key = Key.builder().partitionValue(courseName).build();
      final CourseItem dynamoCourse = coursesTable.getItem(key);
      if (dynamoCourse == null) {
        return null;
      }

      result = new Course(dynamoCourse);

      // store course in cache
      cachedCourses.put(result.name, result);
    } catch (DynamoDbException e) {
      System.err.println("dynamo exception" + e.getMessage());
    }
    return result;
  }

  public static ArrayList<Course> getCourses() throws DynamoDbException {
    ArrayList<Course> result = null;
    if (!cachedCourses.isEmpty()) {
      return new ArrayList<>(cachedCourses.values());
    }

      DynamoDbTable<CourseItem> coursesTable =
              client.table("Courses", TableSchema.fromBean(CourseItem.class));
      PageIterable<CourseItem> results = coursesTable.scan();
      result = results.items()
              .stream()
              .map(Course::new)
              .peek(course -> cachedCourses.put(course.name, course))
              .collect(Collectors.toCollection(ArrayList::new));
      return result;
  }

  // ============== CourseOfferings ===========
  public static ArrayList<CourseOffering> getCourseOfferingsInTerm(final Pair<Term, Year> term) {
    ArrayList<Course> allCourses = DatabaseInterface.getCourses();
    if(allCourses == null) {
      return new ArrayList<>();
    }
    return allCourses
            .stream()
            .map(c -> c.getCourseOffering(term.first, term.second))
            .filter(Objects::nonNull)
            .collect(Collectors.toCollection(ArrayList::new));
  }

  // ============== Requirements ===========
  public static Requirement getRequirement(final Integer id) {
    Requirement result = cachedRequirements.get(id);
    if (result != null) {
      return result;
    }

    try {
      // get req item from dynamo
      DynamoDbTable<RequirementItem> reqsTable =
              client.table("Requirements", TableSchema.fromBean(RequirementItem.class));
      Key key = Key.builder().partitionValue(id).build();
      final RequirementItem dynamoReq = reqsTable.getItem(key);
      if (dynamoReq == null) {
        return null;
      }

      // construct req from item
      final Integer reqType = dynamoReq.getRequirementType();
      if(reqType == RequirementType.CourseRequirement.getValue()) {
        result = new CourseRequirement(dynamoReq);
      } else if (reqType == RequirementType.AdditionalRequirement.getValue()) {
        result = new AdditionalRequirement(dynamoReq);
      } else if (reqType == RequirementType.CreditHourRequirement.getValue()) {
        result = new CreditHourRequirement(dynamoReq);
      } else if (reqType == RequirementType.CourseTypeRequirement.getValue()) {
        result = new CourseTypeRequirement(dynamoReq);
      }

      // store user in cache
      cachedRequirements.put(result.id, result);
    } catch (DynamoDbException e) {
      System.err.println("dynamo exception" + e.getMessage());
    }
    return result;
  }

  // ============== Academic Programs ===========
  public static ArrayList<AcademicProgram> getPrograms() throws DynamoDbException {
    ArrayList<AcademicProgram> result = null;
    if (!cachedPrograms.isEmpty()) {
      return new ArrayList<>(cachedPrograms.values());
    }

    DynamoDbTable<AcademicProgramItem> programsTable =
            client.table("AcademicPrograms", TableSchema.fromBean(AcademicProgramItem.class));
    PageIterable<AcademicProgramItem> results = programsTable.scan();
    result = results.items()
            .stream()
            .map(AcademicProgram::new)
            .peek(prog -> cachedPrograms.put(prog.getDegree(), prog))
            .collect(Collectors.toCollection(ArrayList::new));
    return result;
  }

  public static AcademicProgram getProgram(final String degreeName) {
    AcademicProgram result = null;

    ArrayList<AcademicProgram> programs = DatabaseInterface.getPrograms();

    for(final AcademicProgram p : programs) {
      if(degreeName.equals(p.getDegree())) {
        result = p;
        break;
      }
    }

    return result;
  }

  // ============== Data Loader Functions ===========
  public static void writeCourseItem(final CourseItem course) {
    try {
      DynamoDbTable<CourseItem> courseTable =
              client.table("Courses", TableSchema.fromBean(CourseItem.class));

      courseTable.putItem(course);

    } catch (DynamoDbException e) {
      System.err.println("failed writing course item" + e.getMessage());
    }
  }

  public static void writeRequirementItem(final RequirementItem req) {
    try {
      DynamoDbTable<RequirementItem> reqTable =
              client.table("Requirements", TableSchema.fromBean(RequirementItem.class));

      reqTable.putItem(req);

    } catch (DynamoDbException e) {
      System.err.println("failed writing requirement item" + e.getMessage());
    }
  }

  public static void writeAcademicProgramItem(final AcademicProgramItem program) {
    try {
      DynamoDbTable<AcademicProgramItem> programTable =
              client.table("AcademicPrograms", TableSchema.fromBean(AcademicProgramItem.class));

      programTable.putItem(program);

    } catch (DynamoDbException e) {
      System.err.println("failed writing program item" + e.getMessage());
    }
  }

  // ================== Use Cases =================
  // Enrolments
  public static void enrolInAcademicProgram(final Student s, final AcademicProgram p) {
    try {
      DynamoDbTable<UserItem> usersTable =
              client.table("Users", TableSchema.fromBean(UserItem.class));

      s.programs.add(p);
      UserItem enrolledStudent = new UserItem(s);
      usersTable.updateItem(enrolledStudent);
      cachedUsers.put(s.email, s);
    } catch (DynamoDbException e) {
      throw new EnrolmentException("Database error");
    }
  }

  public static void disenrolFromAcademicProgram(final Student s, final AcademicProgram p) {
    try {
      DynamoDbTable<UserItem> usersTable =
              client.table("Users", TableSchema.fromBean(UserItem.class));

      int enrolledIdx = s.programs.indexOf(p);
      if(enrolledIdx == -1) {
        throw new EnrolmentException("Cannot disenrol from a program you're not enrolled in");
      }
      s.programs.remove(enrolledIdx);
      UserItem disenrolledStudent = new UserItem(s);
      usersTable.updateItem(disenrolledStudent);
      cachedUsers.put(s.email, s);
    } catch (DynamoDbException e) {
      throw new EnrolmentException("Database error");
    }
  }

  public static void enrolInCourseOffering(final Student s, final CourseOffering offering) {
    try {
      DynamoDbTable<UserItem> usersTable =
              client.table("Users", TableSchema.fromBean(UserItem.class));

      s.currentlyEnrolled.add(offering);
      UserItem enrolledStudent = new UserItem(s);
      usersTable.updateItem(enrolledStudent);
      cachedUsers.put(s.email, s);
    } catch (DynamoDbException e) {
        throw new EnrolmentException("Database error");
    }
  }

  public static void disenrolFromCourseOffering(final Student s, final CourseOffering offering) {
    try {
      DynamoDbTable<UserItem> usersTable =
              client.table("Users", TableSchema.fromBean(UserItem.class));

      int enrolledIdx = s.currentlyEnrolled.indexOf(offering);
      if(enrolledIdx == -1) {
        throw new EnrolmentException("Cannot disenrol from a course offering you're not currently enrolled in");
      }
      s.currentlyEnrolled.remove(enrolledIdx);
      UserItem disenrolledStudent = new UserItem(s);
      usersTable.updateItem(disenrolledStudent);
      cachedUsers.put(s.email, s);
    } catch (DynamoDbException e) {
      throw new EnrolmentException("Database error");
    }
  }

  // Viewing Courses
}

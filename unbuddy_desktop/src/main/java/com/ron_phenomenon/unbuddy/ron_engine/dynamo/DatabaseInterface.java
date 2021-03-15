package com.ron_phenomenon.unbuddy.ron_engine.dynamo;

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
import java.util.HashMap;

import com.ron_phenomenon.unbuddy.model.users.User;
import com.ron_phenomenon.unbuddy.model.users.UserType;
import com.ron_phenomenon.unbuddy.model.users.Student;
import com.ron_phenomenon.unbuddy.model.users.Faculty;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.AcademicProgramItem;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.CourseItem;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.RequirementItem;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.UserItem;


public class DatabaseInterface {
  private static DynamoDbEnhancedClient client;

  private static final String accessKey = "AKIAR3FNVHQCJOWKMYKM";
  private static final String secretAccessKey = "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";

  private static HashMap<String, User> cachedUsers = new HashMap<>();
  private static final int numCachedUsers = 5;

  public static void connect() {
    if (client == null) {
      AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretAccessKey);

      DynamoDbClient normalClient = DynamoDbClient.builder().region(Region.CA_CENTRAL_1)
          .credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build();

      client = DynamoDbEnhancedClient.builder().dynamoDbClient(normalClient).build();
    }
  }

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
      if (cachedUsers.size() == numCachedUsers) {
        cachedUsers.remove(cachedUsers.keySet().toArray()[0]);
      }
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

      UserItem item = new UserItem();
      item.setEmail(user.email);
      item.setHashedPassword(user.hashedPassword);
      item.setSalt(user.salt);
      if (user instanceof Student) {
        item.setUserType(0);
        item.setEnrolmentsDocument(((Student) user).getEnrolmentsDocument());
      } else {
        item.setUserType(1);
      }
      usersTable.putItem(item);

      if (cachedUsers.size() > 0) {
        cachedUsers.remove(cachedUsers.keySet().toArray()[0]);
      }
      cachedUsers.put(user.email, user);
    } catch (DynamoDbException e) {
      System.err.println("failed putting" + e.getMessage());
    }
  }

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

}

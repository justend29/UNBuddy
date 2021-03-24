package com.ron_phenomenon.unbuddy.model.courses;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.ron_phenomenon.unbuddy.model.Term;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.CourseItem;
import com.ron_phenomenon.util.Pair;

public class CourseOffering {
  public String courseName;
  public ArrayList<Pair<Term, Year>> times;

  public CourseOffering() { }

  public static ArrayList<CourseOffering> offeringsFromDynamoCourse(final CourseItem dynamoItem) {
    return dynamoItem.getOfferings() == null ? new ArrayList<>() :
            dynamoItem.getOfferings()
            .stream()
            .map(offerings -> {
                CourseOffering offering = new CourseOffering();
                offering.times = Arrays.stream(offerings.split(","))
                        .map(offTime -> {
                          int offTimeInt = Integer.parseInt(offTime);
                          int termInt = offTimeInt / 10000;
                          Year year = Year.of(offTimeInt - termInt * 10000);

                          Pair<Term, Year> time = new Pair<>();
                          if(termInt == Term.Fall.getValue()) {
                              time.first = Term.Fall;
                          } else if (termInt == Term.Winter.getValue()) {
                              time.first = Term.Winter;
                          } else {
                              time.first = Term.Summer;
                          }
                          time.second = year;
                          return time;
                        }).collect(Collectors.toCollection(ArrayList::new));
                if(offering.times.isEmpty()) { offering.times = null; }
                offering.courseName = dynamoItem.getName();
                return offering;
            })
            .collect(Collectors.toCollection(ArrayList::new));
  }

  public String toString() {
    StringBuilder result = new StringBuilder();
    for(int i =0; i<times.size(); ++i) {
      int time = 10000 * times.get(i).first.getValue() + times.get(i).second.getValue();
      result.append(courseName).append("#").append(time);
      if(i < times.size() - 1) {
          result.append(",");
      }
    }
    return result.toString();
  }

  public int hashCode() {
      return this.toString().hashCode();
  }
}

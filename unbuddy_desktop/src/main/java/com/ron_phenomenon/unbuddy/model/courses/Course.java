package com.ron_phenomenon.unbuddy.model.courses;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.ron_phenomenon.unbuddy.model.Term;
import com.ron_phenomenon.unbuddy.model.requirements.Requirement;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.DatabaseInterface;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.CourseItem;
import com.ron_phenomenon.util.Pair;

public class Course {
    public final String name;
    public final int creditHours;
    public final String description;
    public ArrayList<Requirement> dependencies;
    public ArrayList<CourseOffering> offerings;

    public Course(final CourseItem dynamoItem) {
        this.name = dynamoItem.getName();
        this.creditHours = dynamoItem.getCreditHours();
        this.description = dynamoItem.getDescription();
        this.dependencies = dynamoItem.getDependencies() == null ? new ArrayList<>() :
                dynamoItem.getDependencies()
                        .stream()
                        .map(DatabaseInterface::getRequirement)
                        .collect(Collectors.toCollection(ArrayList::new));
        this.offerings = CourseOffering.offeringsFromDynamoCourse(dynamoItem);
    }

    public CourseOffering getCourseOffering(final String term, final String year) {
        Term t;
        int termInt = Integer.parseInt(term);
        if (termInt == Term.Fall.getValue()) {
            t = Term.Fall;
        } else if (termInt == Term.Winter.getValue()) {
            t = Term.Winter;
        } else {
            t = Term.Summer;
        }
        Year y = Year.of(Integer.parseInt(year) - termInt * 1000);
        return getCourseOffering(t, y);
    }

    public CourseOffering getCourseOffering(final Term term, final Year year) {
        CourseOffering result = null;
        for (final CourseOffering offering : offerings) {
            for (final Pair<Term, Year> time : offering.times) {
                if (time.first.equals(term) && time.second.equals(year)) {
                    result = offering;
                    return result;
                }
            }
        }
        return result;
    }

    public HashSet<CourseOffering> getCourseOfferingsInTerm(final Pair<Term, Year> time) {
        return this.offerings
                .stream()
                .filter(offering -> offering.times.contains(time))
                .collect(Collectors.toCollection(HashSet::new));
    }

    public int hashCode() {
        return this.name.hashCode();
    }
}

package com.ron_phenomenon.unbuddy.ron_engine.dynamo.data_loader;

import java.io.FileNotFoundException;
import java.util.HashSet;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.DatabaseInterface;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.AcademicProgramItem;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.mappings.RequirementItem;


public class DataLoader {

    // data to load into dynamo db
    private HashSet<AcademicProgramItem> programs;
    private HashSet<RequirementItem> requirements;

    public static void run() {
        DatabaseInterface.connect();

        // Course CSV
        HashSet<RequirementItem> requirements = null;
        try {
            requirements = CourseItemsParser.parseFrom(
                    "/home/justen/school/third-year/term1/tme3313/group-project/unbuddy/data_collection/courseItems.csv");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        if (requirements != null) {
            requirements.stream().forEach(req -> {
                DatabaseInterface.writeRequirementItem(req);
            });
        }
        requirements.clear();
        requirements = null;

        // ECE Program CSV
        try {
            requirements = AcademicProgramItemsParser.parseFrom(
                    "/home/justen/school/third-year/term1/tme3313/group-project/unbuddy/data_collection/ee_academicProgramItems.csv");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        if (requirements != null) {
            requirements.stream().forEach(req -> {
                DatabaseInterface.writeRequirementItem(req);
            });
        }
        requirements.clear();
        requirements = null;

        // BBA Program CSV
        try {
            requirements = AcademicProgramItemsParser.parseFrom(
                    "/home/justen/school/third-year/term1/tme3313/group-project/unbuddy/data_collection/bba_academicProgramItems.csv");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        if (requirements != null) {
            requirements.stream().forEach(req -> {
                DatabaseInterface.writeRequirementItem(req);
            });
        }
    }
}

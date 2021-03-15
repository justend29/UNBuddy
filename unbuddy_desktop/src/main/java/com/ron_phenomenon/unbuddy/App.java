package com.ron_phenomenon.unbuddy;

import com.ron_phenomenon.unbuddy.model.users.User;
import com.ron_phenomenon.unbuddy.model.users.UserType;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.DatabaseInterface;
import com.ron_phenomenon.unbuddy.ron_engine.dynamo.data_loader.DataLoader;

public class App {
    public static void main(String[] args) {
        DatabaseInterface.connect();


        // Data Loader Mode
        DataLoader.run();

        // Desktop Mode
        // try {
        // final User testUser =
        // User.createAccount("testCreate", "testPassword", UserType.Faculty);
        // if (testUser == null) {
        // System.err.println("Account already exists");
        // }
        // } catch (Exception e) {
        // System.out.println("failure creating new user" + e.getMessage() + e.getStackTrace());
        // }

        // try {
        // final User testUser2 = User.login("testCreate2", "testPassword", UserType.Student);
        // if (testUser2 == null) {
        // System.err.println("Incorrect email or password");
        // }
        // } catch (Exception e) {
        // System.out.println("bad" + e.getMessage());
        // e.printStackTrace();
        // }

        System.out.println("Hello World!");
    }
}

# UNBuddy

UNBuddy is a prototype of a course dependency manager and was created as the term project of TME3313.
Its purpose is to:

1. Illustrate course dependencies and program requirements to students and automatically determine if a course selection fulfils a given students needs at the moment it's selected.
2. Compile the various lists of course options, such as eligible basic science electives, technical electives, and complementary study electives, into a single location.
3. Provide students with an automatic system to validate their dependencies are being met.

The repository contains all implementation details and code related to the UNBuddy prototype for TME3313.

## Project Layout

The project uses the standard Maven package layout. More information can be found [here](
https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html).

### Components

There are three main components of UNBuddy:

1. the desktop user interface
2. *ron-engine*, the backend engine which reconciles course dependencies
3. *data-model*, the

In a perfect world, these would be independent artifacts that link to each
other, such that various other clients (web-apps, mobile-apps, CLI, etc.)
could be easily made. However, as this is only a prototype and no other
clients will be made, they are coupled to the same JAR artifact.

### Entry Point

**unbuddy/unbuddy_desktop/src/main/java/com/ron_phenomenon/unbuddy/App.java** is the main entry point for the desktop user interface, which then calls functions from ron-engine.

### Formatting

The file *eclipse-java-google-style.xml* specifies the auto-formatting style
for IDEs or autoformatters to use. Information about using the format file in
vs-code or eclipse can be founde
[here](https://github.com/redhat-developer/vscode-java/wiki/Formatter-settings)

## Building

The Apache Maven *Assembly* plugin is used to create a fat JAR containing the
compiled project binaries and all required dependencies. Its invocation has
been bound to the *package* Maven goal in the *pom.xml* file. This will
automatically invoke the plugin to generate this fat JAR in the *target*
directory when `mvn package` is called. More information about Maven's
phases, including *package* can be found in the section *Maven Phases* of
[this
article](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

Ex:

```bash
cd unbuddy_desktop
mvn package
java -jar target/unbuddy_desktop-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Any Java IDE should support Maven, too, so that can be set up.

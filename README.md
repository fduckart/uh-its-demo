Demonstration of various utility functions.


##### Build Tool
Apache maven (version 3.2.3+).

If necessary, be sure to set up a M2_REPO environment variable.

##### Java
You'll need a Java JDK to build and run the project (version 1.8).

The files for the project are kept in a code repository,
available from here:

https://github.com/fduckart/uh-its-demo

##### Building
Install the necessary project dependencies:

    $ ./mvnw install

To build a deployable war file for local development, if preferred:

    $ ./mvnw clean package

You should have a deployable jar file in the target directory.

##### Running Unit Tests
The project includes Unit Tests for various parts of the system.
For this project, Unit Tests are defined as those tests that will
rely on only the local development computer.
A development build of the application will run the Unit Tests.

You can also run specific Unit Tests using the appropriate command
line arguments.

To run the Unit Tests with a standard build:

    $ ./mvnw clean test

To run a test class:

    $ ./mvnw clean test -Dtest=NumbersTest

To run a single method in a test class:

    $ ./mvnw clean test -Dtest=NumbersTest#round


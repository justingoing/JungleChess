# Development Manual

## Development Environment

The development environment requires a Java SDK version 1.8 or newer and JUnit 5 or newer and Maven.  
The program can be compiled and ran using the IDE of choice although document only covers the Linux Command Line and IntelliJ.

### Command Line Instructions

These instructions were written for a Linux command line and are untested on any other platform.

#### Running the Tests

There are not currently any known ways to reliably run the tests from the command line.

#### Running the Code

1. Compile the server code with `mvn package` (this may require `mvn install` to be run first).
2. Run the server with `java -cp ../target/cs414-f18-001-Method-Men-1.0-SNAPSHOT.jar edu.colostate.cs.cs414.method_men.jungle.server.TCPServer`.
3. Compile the client code with `javac src/main/edu/colostate/cs/cs414/method_men/jungle/client/*.java src/main/edu/colostate/cs/cs414/method_men/jungle/client/*/*.java`.
4. Run the client with `java -cp src/main/ edu.colostate.cs.cs414.method_men.jungle.client.socket.Client` (note that you may need to run multiple clients to fully test the game and this may require multiple terminals).

### IntelliJ instructions

#### Setup

First clone or download the repository into an IntelliJ project.

Make sure the Java SDK is set as the SDK for the project (File -> Project Settings -> Project SDK).

Then mark the source and test directories:
1. Mark src/main as the Sources Root directory (\<right click\> -> Mark Directory As -> Sources Root).
2. Mark src/test as the Test Sources Root directory (\<right click\> -> Mark Directory As -> Test Sources Root).

The configure the project as a Maven Project:
1. Open a terminal window within IntelliJ.
2. Run the command `mvn package`.
3. On the right hand side, there should be a tab titled "Maven Projects".
4. Within the Maven Projects window, click the "Reimport All Maven Projects" button (it looks like a refresh button).

#### Running the Tests

To run the tests: right click on the src/test directory and select "Run 'All Tests'"

#### Running the Code

There are two programs that must be run, the server and the client.

First run the server:
* Right click on the TCPServer file located in the source code under the "server" directory, then choose "Run 'TCPServer.main()'

Then run the client:
* Right click on the TCPServer file located in the source code under "client/socket", then choose "Run 'Client.main()'"

## Contributor Conduct

### Coding Conventions

For any conventions not mentioned in this document, refer to [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).

#### Indentations:

All indentations should be 4 spaces, not tab characters.

#### Curly Braces:

There should never be a line break before the open brace.
	For Example:
``` 
if (true) {
	// code here
} else {
	// code here
}
```

### Branch Naming

All branches must be named with the following convention:
`<username>-<subject>`

* Username is the Github username of the branch's creator.
* Subject is the subject of the code that will be worked on.
	For example: a branch made by user "foo" to work on the database should be called "foo-database"
  
### Branch Deletion

A branch should be deleted by its creator after the pull request associated with the branch is approved and merged.
Note: This is not the responsibility of the approver.

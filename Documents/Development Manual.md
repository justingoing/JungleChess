# Development Manual

## Development Environment

The development environment requires no dependencies other than a Java SDK version 1.8 or newer and JUnit 5 or newer.  
The program can be compiled and ran using the IDE of choice.

### IntelliJ instructions
#### Dependencies
Make sure that Java 1.8 or newer and JUnit 5 or newer are installed.

#### Setup
First clone or download the repository into an IntelliJ project.

Make sure the Java SDK is set as the SDK for the project (File -> Project Settings -> Project SDK).

Mark src/main as the Source Root directory (\<right click\> -> Mark Directory As -> Sources Root).
Mark src/test as the Test Sources Root directory (\<right click\> -> Mark Directory As -> Test Sources Root).

#### Running tests and code
To run the tests: right click on the src/test directory and select "Run 'All Tests'"

To run the code: Run -> Run... -> Main

## Running Tests
See the above section for information on running tests in an IDE.

Information on running tests from the command line will be added later.

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

### Branch naming
All branches must be named with the following convention:
`<username>-<subject>`

* Username is the Github username of the branch's creator.
* Subject is the subject of the code that will be worked on.
	For example: a branch made by user "foo" to work on the database should be called "foo-database"
  
### Branch Deletion
A branch should be deleted by its creator after the pull request associated with the branch is approved and merged.  
Note: This is not the responsibility of the approver.
